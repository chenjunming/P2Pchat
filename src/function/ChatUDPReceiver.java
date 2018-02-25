/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.IndexFrame;
import bean.MyVector;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.AbstractMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cjm
 */
public class ChatUDPReceiver implements Runnable {

    //private Map<String,String> userMap;
    private MyVector userVector;
    private IndexFrame indexFrame;

    public ChatUDPReceiver(MyVector userVector, IndexFrame indexFrame) {
        this.indexFrame = indexFrame;
        this.userVector = userVector;
    }

    @Override
    public void run() {
        while (true) {
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(3335);
                // 创建一个数据报（接收容器）
                byte[] bys = new byte[1024];
                int length = bys.length;
                DatagramPacket dp = new DatagramPacket(bys, length);
                // 调用Socket对象的接收方法接收数据
                ds.receive(dp);//阻塞式
                // 解析数据包，并显示
                // 获取对方的IP
                InetAddress address = dp.getAddress();
                String ip = address.getHostAddress();

                byte[] bys2 = dp.getData();//获取数据缓冲区
                int len = dp.getLength();
                String s = new String(bys2, 0, len);
                if (s.contains("@username")) {
                    String username = s.substring(s.indexOf("@username") + "@username".length()).trim();
                    boolean flag = true;
                    for (Map.Entry<String, String> entry : userVector) {
                        if (entry.getKey().equals(username)) {
                            flag = false;
                            Map<String, Long> liveMap = userVector.getLiveMap();
                            liveMap.put(username, 0l);
                            break;
                        }
                    }
                    //存放用戶數據
                    if (flag) {
                        AbstractMap.SimpleEntry userEntry = new AbstractMap.SimpleEntry(username, ip);
                        userVector.add(userEntry);
                        indexFrame.updataMyIndex();

                        System.out.println("UDP收到" + ip + ":" + username);
                    }
                }
//                } else if (s.contains("@ServerPort")) {
//                // 进入单人聊天
//                    // 获取服务器端口
//                    String[] serverPortStrings = s.split("@ServerPort");
//                    int serverPort = -1;
//                    for (int i = 0; i < serverPortStrings.length; i++) {
//                        if (!serverPortStrings[i].trim().isEmpty()) {
//                            serverPort = Integer.parseInt(serverPortStrings[1]);
//                        }
//                    }
//                    System.out.println(ip + ":" + serverPort);
//                    // 建立TCP连接
//                    Socket client_s = new Socket(ip, serverPort);
//                    OutputStream os = client_s.getOutputStream();
//                    os.write(bys);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //释放资源
            if (ds != null) {
                ds.close();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatUDPReceiver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
