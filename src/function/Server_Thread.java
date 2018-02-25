/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import View.SingleFrame;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cjm
 */
public class Server_Thread implements Runnable {

    //随机生成一个端口号给当前服务器
    private static final int port = new Random().nextInt(16383) + 49152;
    private InputStream is;
    private String username;
    public Server_Thread(String username){
        this.username = username;
    }
    public static int getServerPort() {
        return port;
    }

    // 发一个UDP探测包，告诉对等方客户端本机服务器的端口号
//    public void sendUDPtest(String clientIP) throws SocketException, UnknownHostException, IOException {
//        // 创建发送端Socket对象
//        DatagramSocket ds = new DatagramSocket();
//        // 创建数据，并把数据打包
//        byte[] bys = ("@ServerPort" + port).getBytes();
//        int length = bys.length;
//        // IP地址对象
//        InetAddress address = InetAddress.getByName(clientIP);
//        DatagramPacket dp = new DatagramPacket(bys, length, address, 3334);
//        ds.send(dp);
//        ds.close();
//    }

    @Override
    public void run() {
        try {
            // 创建服务器Socket对象
            ServerSocket ss = new ServerSocket(port);
            while (true) {

                // 监听客户端的连接
                Socket s = ss.accept();
                //获取输入流
                is = s.getInputStream();
                byte[] bys = new byte[1024];
                int len = is.read(bys);
                String str = new String(bys, 0, len).trim();
                String[] split = str.split("@chat");
                String receivername = split[1];
                String mes = split[2];
                System.out.println(receivername + ":" + mes);
                SingleFrame singleFrame = SingleFrame.getSingleFrame(username,receivername);
                singleFrame.setVisible(true);
                singleFrame.setMessage(mes);
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
