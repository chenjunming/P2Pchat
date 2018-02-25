/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author cjm
 */
public class ChatSender implements Runnable {

    private String groupname;
    private String username;
    private JFrame loginFrame;

    public ChatSender(JFrame loginFrame, String username, String groupname) {
        this.loginFrame = loginFrame;
        this.username = username;
        this.groupname = groupname;
    }

    @Override
    public void run() {
        try {
            // 根据所在组，创建或加入一个多播组
            InetAddress group = InetAddress.getByName(groupname);
            MulticastSocket s = new MulticastSocket(3333);
            //加入多播组 
            s.joinGroup(group);
            String msg = "@username" + username+"@username"+"@ServerPort"+Server_Thread.getServerPort()+"@ServerPort";
            while (true) {
                ByteArrayOutputStream ostream = new ByteArrayOutputStream();
                DataOutputStream dataStream = new DataOutputStream(ostream);
                dataStream.writeUTF(msg);
                dataStream.close();
                byte[] data = ostream.toByteArray();
                //创建一个数据报封装多播信息 
                DatagramPacket hi = new DatagramPacket(data, data.length, group, 3333);  //发送 
                s.send(hi);
                
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
