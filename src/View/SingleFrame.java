/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import bean.MyVector;
import function.Client;
import function.Server_Thread;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

/**
 *
 * @author cjm
 */
public class SingleFrame extends javax.swing.JFrame implements ActionListener, KeyListener {

    private IndexFrame indexFrame;
    private String username;
    private String receiver;

    //private String localIp;
    // 获取单人聊天框
    public static SingleFrame getSingleFrame(String username, String receiver) {
        Map<String, SingleFrame> singleFrameMap = IndexFrame.getSingleFrameMap();
        if (singleFrameMap.get(receiver) != null) {
            return singleFrameMap.get(receiver);
        } else {
            return new SingleFrame(username, receiver);
        }
    }

    /**
     * Creates new form SingleFrame
     */
    public SingleFrame(String username, String receiver) {
        initComponents();
        this.username = username;
        this.receiver = receiver;
        //this.localIp = ip;
        this.setTitle("与  " + receiver + "  单人聊天");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        jb_send.addActionListener(this);
        jb_close.addActionListener(this);
        jtp_inputMess.addKeyListener(this);
        jb_sendDoc.addActionListener(this);
        Map<String, SingleFrame> singleFrameMap = IndexFrame.getSingleFrameMap();
        singleFrameMap.put(receiver, this);
    }

    public void setMessage(String message) {
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        String date = form.format(new Date());
        String mess = receiver + "   " + date + "\n" + message;
        jta_disMess.append(mess + "\n");
        jta_disMess.setCaretPosition(jta_disMess.getText().length());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_send) {
            try {
                // 建立TCP连接
                //new Client(username, receiver).connect();
                //Server_Thread server_Thread = new Server_Thread();
                //server_Thread.sendUDPtest(localIp);
                MyVector userVector = LoginFrame.getUserVector();
                String serverIp = null;
                for (Map.Entry<String, String> entry : userVector) {
                    if (entry.getKey().equals(receiver)) {
                        serverIp = entry.getValue();
                    }
                }
                if (serverIp == null) {
                    JOptionPane.showMessageDialog(this, "对方已下线", "提示",
                            JOptionPane.OK_OPTION);
                    dispose();
                    //throw new Exception("别特么逗我了，集合里面找不到这个IP");
                }
                Client client = new Client(username, serverIp, receiver);
                client.connect();
                String str = jtp_inputMess.getText();
                str.trim();
                jtp_inputMess.setText(null);
                if (str.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "信息不能为空");
                } else {
                    client.transMess(str);
                    SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
                    String date = form.format(new Date());
                    String mess = username + "   " + date + "\n" + str;
                    jta_disMess.append(mess + "\n");
                    jta_disMess.setCaretPosition(jta_disMess.getText().length());

//				String mess = client.username + "  " + date + "\n" + str;
//				jta_disMess.append(mess + "\n");
//				jta_disMess.setCaretPosition(jta_disMess.getText().length());
//				int index = client.username_online.indexOf(this.getTitle());
//				String info = client.username + "@single" + client.getThreadID() + "@single" +
//								(int)client.clientuserid.get(index) + "@single" + 
//								mess + "@single";
//				try {
//					client.dos.writeUTF(info);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
                }
            } catch (Exception ex) {
                Logger.getLogger(SingleFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == jb_close) {
            dispose();
        }
        if (e.getSource() == jb_sendDoc) {
            try {
                //传文件
                MyVector userVector = LoginFrame.getUserVector();
                String serverIp = null;
                for (Map.Entry<String, String> entry : userVector) {
                    if (entry.getKey().equals(receiver)) {
                        serverIp = entry.getValue();
                    }
                }
                if (serverIp == null) {
                    JOptionPane.showMessageDialog(this, "对方已下线", "提示",
                            JOptionPane.OK_OPTION);
                    dispose();
                    //throw new Exception("别特么逗我了，集合里面找不到这个IP");
                }
                Client client = new Client(username, serverIp, receiver);
                client.connect();
                JFileChooser jfc = new JFileChooser();
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showDialog(new JLabel(), "选择要上传的文件");
                File file = jfc.getSelectedFile();
                String filePath = file.getAbsolutePath();
                String fileName = file.getName();
                Long fileLength = file.length();
                
                System.out.println("文件:" + filePath);
                System.out.println("文件名：" + fileName);
                System.out.println("文件大小："+fileLength+"字节");
                DataOutputStream dos = client.getDos();
                dos.writeUTF("@filename"+file.getName()+"@filename");
                dos.writeUTF("@fileLength"+file.length()+"@fileLength");
                boolean fileTransAllowance = client.getFileTransAllowance();
                //jta_disMess.add();
                System.out.println(jfc.getSelectedFile().getName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jta_disMess = new javax.swing.JTextArea();
        jb_close = new javax.swing.JButton();
        jb_send = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtp_inputMess = new javax.swing.JTextPane();
        jb_sendDoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("聊天消息");

        jScrollPane1.setFont(new java.awt.Font("宋体", 0, 20)); // NOI18N

        jta_disMess.setEditable(false);
        jta_disMess.setColumns(20);
        jta_disMess.setRows(5);
        jScrollPane1.setViewportView(jta_disMess);

        jb_close.setText("关闭");

        jb_send.setText("发送");

        jtp_inputMess.setFont(new java.awt.Font("宋体", 0, 24)); // NOI18N
        jScrollPane2.setViewportView(jtp_inputMess);

        jb_sendDoc.setText("发送文件");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(10, 270, Short.MAX_VALUE)
                        .addComponent(jb_close, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jb_send, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jb_sendDoc))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jb_sendDoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jb_close)
                    .addComponent(jb_send)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jb_close;
    private javax.swing.JButton jb_send;
    private javax.swing.JButton jb_sendDoc;
    private javax.swing.JTextArea jta_disMess;
    private javax.swing.JTextPane jtp_inputMess;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (e.getSource() == jtp_inputMess) {
                jb_send.doClick();

            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
