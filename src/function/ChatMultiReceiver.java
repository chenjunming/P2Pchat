/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.IndexFrame;
import bean.MyVector;
import bean.UserRepeatException;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;

/**
 *
 * @author cjm
 */
public class ChatMultiReceiver implements Runnable {

    private MulticastSocket ds = null;
    private String multicastGroup = null;
    private InetAddress receiveAddress = null;
    private String username;
    //private Map<String, String> userMap;
    private MyVector userVector;
    private IndexFrame indexFrame;

    public void setIndexFrame(IndexFrame indexFrame) {
        this.indexFrame = indexFrame;
    }
    
    public ChatMultiReceiver(String username, String multicastGroup, MyVector userVector, IndexFrame indexFrame) {
        this.username = username;
        this.multicastGroup = multicastGroup;
        this.userVector = userVector;
        this.indexFrame = indexFrame;
    }
    public String testRepeatUsername() {
        String result=null;
         try {
            // 利用UDP多播可以端口复用的原理，传输ServerPort
            ds = new MulticastSocket(3333);
                receiveAddress = InetAddress.getByName(multicastGroup);
                ds.joinGroup(receiveAddress);
            long lastTime = System.currentTimeMillis();
            
            while (true) {
                // 接收多播报文
                if(System.currentTimeMillis() - lastTime>1000)break;
                 byte buf[] = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, 1024);
                ds.setSoTimeout(1000);
                ds.receive(dp);
                DataInputStream istream = new DataInputStream(new ByteArrayInputStream(dp.getData(), dp.getOffset(), dp.getLength()));
                String msg = istream.readUTF();
                boolean flag = true;
                //String receiveUsername = new String(dp.getData()).substring(new String(dp.getData()).indexOf("@username") + "@username".length() + 1).trim();
                String receiveUsername  = msg.trim().split("@username")[1];
                // 如果重名
                if(username.equals(receiveUsername) ){
                    result = "在该所在组已有重名用户，请重新选择用户名";
                }

            }
        } catch (Exception e) {
            //
        }finally{
             if(ds!=null){
                 ds.close();
             }
             return result;
         }
    }
    @Override
    public void run() {
        try {
            // 利用UDP多播可以端口复用的原理，传输ServerPort
            ds = new MulticastSocket(3333);
                receiveAddress = InetAddress.getByName(multicastGroup);
                ds.joinGroup(receiveAddress);
            long lastTime = System.currentTimeMillis();
            while (true) {
                
                // 接收多播报文
                 byte buf[] = new byte[1024];
                DatagramPacket dp = new DatagramPacket(buf, 1024);
                ds.receive(dp);
                DataInputStream istream = new DataInputStream(new ByteArrayInputStream(dp.getData(), dp.getOffset(), dp.getLength()));
                String msg = istream.readUTF();
                boolean flag = true;
                //String receiveUsername = new String(dp.getData()).substring(new String(dp.getData()).indexOf("@username") + "@username".length() + 1).trim();
                String receiveUsername  = msg.trim().split("@username")[1];
                String[] split = msg.trim().split("@ServerPort");
                int serverPort = -1;
                if(split.length>=2){
                    serverPort = Integer.parseInt(split[1]);
                }
                if(serverPort>0 && serverPort<65535)
                Client.addServerPort(receiveUsername, serverPort);
                // 存放用户信息
                if(receiveUsername.trim().isEmpty())continue;
                for (Map.Entry<String, String> entry : userVector) {
                    if (entry.getKey().equals(receiveUsername)) {
                        flag = false;
                        Map<String, Long> liveMap = userVector.getLiveMap();
                        liveMap.put(receiveUsername,0l);
                        break;
                    }
                }
                if (flag) {
                    AbstractMap.SimpleEntry<String, String> userEntry = new AbstractMap.SimpleEntry(receiveUsername.trim(), dp.getAddress().toString().substring(1));
                    userVector.add(userEntry);
                    indexFrame.updataMyIndex();
                    
                    // userMap.put(username, dp.getAddress().toString());
                    System.out.println("receive client message : " + receiveUsername);
                    System.out.println("IP地址为:" + dp.getAddress().toString().substring(1));
//                    String host = InetAddress.getLocalHost().getHostAddress();
//                    String sendserString = dp.getAddress().toString().substring(1);
                }
                
                // 发应答消息(UDP)
                DatagramSocket ds1 = new DatagramSocket();
                byte[] bys = ("@username" + username).getBytes();
                int length = bys.length;
                // IP地址对象
                InetAddress address = dp.getAddress();
                DatagramPacket dp1 = new DatagramPacket(bys, length, address, 3334);
                // 发送
                ds1.send(dp1);
                ds1.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
