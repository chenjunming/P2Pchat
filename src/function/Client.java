/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package function;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cjm
 */
public class Client implements Runnable{

    private String username;
    private String serverIp;
    private Socket c_socket;
    public DataInputStream dis = null;
    public DataOutputStream dos = null;
    
    private static Map<String,Integer> serverPortMap = new HashMap<String, Integer>();

    public static Map<String, Integer> getServerPortMap() {
        return serverPortMap;
    }

    public static void setServerPortMap(Map<String, Integer> serverPortMap) {
        Client.serverPortMap = serverPortMap;
    }
    private String serverName;
    
    public DataInputStream getDis() {
        return dis;
    }

    public void setDis(DataInputStream dis) {
        this.dis = dis;
    }

    public DataOutputStream getDos() {
        return dos;
    }

    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
    
    public static void addServerPort(String serverName,int serverPort){
        serverPortMap.put(serverName, serverPort);
    }
    public Client( String username , String serverIp,String serverName) {
        this.username = username;
        this.serverIp = serverIp;
        this.serverName = serverName;
        
    }
    
    public String connect() {
        // 建立TCP连接
        String login_mess = null;
        try {
            c_socket = new Socket(serverIp, serverPortMap.get(serverName));
        } catch (NumberFormatException e) {
            login_mess = "连接的对等方port为整数,取值范围为：1024<port<65535";
            return login_mess;
        } catch (UnknownHostException e) {
            login_mess = "主机地址错误";
            return login_mess;
        } catch (IOException e) {
            login_mess = "连接服务其失败，请稍后再试";
            return login_mess;
        }
        getDataInit();
        
        return "true";

    }
    
    private void getDataInit() {
        try {
            dis = new DataInputStream(c_socket.getInputStream());
            dos = new DataOutputStream(c_socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void transMess(String mess) {
        try {
            dos.writeUTF("@chat"+username + "@chat" + mess + "@chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitChat() throws IOException {
        dos.close();
        dis.close();

    }

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean getFileTransAllowance() {
        
        return false;
    }
}
