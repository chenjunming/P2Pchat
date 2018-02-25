/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import bean.MyVector;
import bean.UserRepeatException;
import function.ChatMultiReceiver;
import function.ChatSender;
import function.ChatUDPReceiver;
import function.Server_Thread;
import function.TimerClock;
import java.awt.Container;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 *
 * @author cjm
 */
public class LoginFrame extends javax.swing.JFrame {

    private List<String> usernameList = null;
    private List<String> groupList = null;
    // 线程间传递用户列表
    //private static Map<String, String> userMap = new HashMap<String, String>();
    private static MyVector userVector = new MyVector(new LinkedHashSet<Map.Entry<String, String>>());
    private IndexFrame indexFrame = null;

    public static MyVector getUserVector() {
        return userVector;
    }

    public static void setUserVector(MyVector userVector) {
        LoginFrame.userVector = userVector;
    }

    /**
     * Creates new form NewJFrame
     */
    public LoginFrame() {
        initComponents();
        try {
            loadUsernameAndGroup();
        } catch (Exception e) {
            e.printStackTrace();
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        exit = new javax.swing.JButton();
        login = new javax.swing.JButton();
        cob_username = new javax.swing.JComboBox();
        cob_group = new javax.swing.JComboBox();
        cb_isMindUsername = new javax.swing.JCheckBox();
        cb_isMindGroup = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("P2P聊天系统");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel2.setText("用户名");

        jLabel3.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        jLabel3.setText("所在组");

        exit.setText("退出");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        login.setText("登录");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        cob_username.setEditable(true);
        cob_username.setToolTipText("请输入用户名");
        cob_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cob_usernameActionPerformed(evt);
            }
        });

        cob_group.setEditable(true);
        cob_group.setToolTipText("请输入所在组");

        cb_isMindUsername.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        cb_isMindUsername.setSelected(true);
        cb_isMindUsername.setText("记住用户名");
        cb_isMindUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_isMindUsernameActionPerformed(evt);
            }
        });

        cb_isMindGroup.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
        cb_isMindGroup.setSelected(true);
        cb_isMindGroup.setText("记住所在组");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                        .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cob_username, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cob_group, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cb_isMindGroup)
                    .addComponent(cb_isMindUsername))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cob_username, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_isMindUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cob_group, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_isMindGroup))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exit, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        // 结束程序
        System.exit(0);
    }//GEN-LAST:event_exitActionPerformed

    private void cb_isMindUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_isMindUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_isMindUsernameActionPerformed

    private void cob_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cob_usernameActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_cob_usernameActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        // 获取用户名和所在组
        String username = cob_username.getSelectedItem().toString();
        String group = cob_group.getSelectedItem().toString();
        // 校验用户名和所在组是否合法
        if (username.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "用户名不能为空！");
            return;
        }
        if (group.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "所在组不能为空！");
            return;
        } else {
            try {
                String[] split = group.split("\\.");
                if (split.length != 4) {
                    JOptionPane.showMessageDialog(this, "所在组输入格式错误");
                    return;
                }
                for (int i = 0; i < split.length; i++) {
                    Integer num = Integer.parseInt(split[i]);
                    if (num < 0 || num > 255) {
                        JOptionPane.showMessageDialog(this, "所在组输入格式错误");
                        return;
                    }
                    if (i == 0 && (num <= 196 || num > 255)) {
                        JOptionPane.showMessageDialog(this, "所在组输入格式错误");
                        return;
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "所在组输入格式错误（必须为正整数）");
                return;
            }
        }
        // 获取是否选中保存用户名和所在组
        boolean isSaveUsername = cb_isMindUsername.isSelected();
        boolean isSaveGropu = cb_isMindGroup.isSelected();
        Document doc = readXml();
        // 如果保存，存放在本地
        if (!usernameList.contains(username) && isSaveUsername) {
            Element rootele = doc.getRootElement();
            List<Element> elements = rootele.elements("list");
            for (Element element : elements) {
                Attribute attribute = element.attribute("name");
                if (attribute.getValue().equals("username")) {
                    Element el_username = element.addElement("username");
                    el_username.setText(username);
                }
            }
        }
        if (!groupList.contains(group) && isSaveGropu) {
            Element rootele = doc.getRootElement();
            List<Element> elements = rootele.elements("list");
            for (Element element : elements) {
                Attribute attribute = element.attribute("name");
                if (attribute.getValue().equals("group")) {
                    Element el_username = element.addElement("group");
                    el_username.setText(group);
                }
            }
        }
        try {
            write2XML(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ChatMultiReceiver chatMultiReceiver = new ChatMultiReceiver(username, group, userVector, indexFrame);
        
        //JOptionPane.showMessageDialog(this,"请稍等，正在搜索该分组中是否有重复用户名");
//        JOptionPane op = new JOptionPane("请稍等，正在搜索该分组中是否有重复用户名", JOptionPane.INFORMATION_MESSAGE);
//        final JDialog dialog = op.createDialog("检查用户名");
//        dialog.add(new JProgressBar());
//        // 创建一个新计时器
//        Timer timer = new Timer();
//
//        // 1秒后执行该任务
//        timer.schedule(new TimerTask() {
//            public void run() {
//                dialog.setVisible(false);
//                dialog.dispose();
//            }
//        }, 1000);
//        
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        dialog.setAlwaysOnTop(true);
//        dialog.setModal(false);
//        dialog.setVisible(true);
        //op.setVisible(true);
        //
        String result = chatMultiReceiver.testRepeatUsername();
        if (result != null) {
            JOptionPane.showMessageDialog(this, result);
            return;
        }
        indexFrame = new IndexFrame(username, userVector, group);
        try {
            userVector.add(new AbstractMap.SimpleEntry(username, InetAddress.getLocalHost().getHostAddress().toString()));
            indexFrame.updataMyIndex();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }

        // 创建接收多播报文线程
        chatMultiReceiver.setIndexFrame(indexFrame);
        Thread multiReceiverThread = new Thread(chatMultiReceiver);
        multiReceiverThread.start();
        System.out.println(multiReceiverThread.getName());

        // 创建接收UDP报文线程
//        Thread udpReceiverThread = new Thread(new ChatUDPReceiver(userVector,indexFrame));
//        udpReceiverThread.start();
//        System.out.println(udpReceiverThread.getName());
        // 发送多播报文
        new Thread(new ChatSender(indexFrame, username, group)).start();
        // 创建计时器
        new Thread(new TimerClock(userVector, indexFrame)).start();
        new Thread(new Server_Thread(username)).start();
        this.setVisible(false);


    }//GEN-LAST:event_loginActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });

    }

    private void loadUsernameAndGroup() throws XmlPullParserException, FileNotFoundException, IOException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        // 获取工厂
        XmlPullParserFactory parserFactory = XmlPullParserFactory.newInstance();
        // 获取到xml的解析器
        XmlPullParser parser = parserFactory.newPullParser();
        // 给解析器设置一个输入源
        parser.setInput(new FileReader(new File(new File("").getAbsolutePath() + "\\src\\res\\data\\username_group.xml")));
        int eventType = parser.getEventType();
        boolean flag = true;
        while (eventType != XmlPullParser.END_DOCUMENT && flag) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    //开始标签
                    // parser.getName 获取当前事件对应的元素名字
                    if ("list".equals(parser.getName())) {
                        if (parser.getAttributeValue(0).equals("username")) {
                            usernameList = new ArrayList<>();
                        }
                        if (parser.getAttributeValue(0).equals("group")) {
                            groupList = new ArrayList<>();
                        }
                    }
                    if ("username".equals(parser.getName())) {
                        usernameList.add(parser.nextText());
                    }
                    if ("group".equals(parser.getName())) {
                        groupList.add(parser.nextText());
                    }
                    break;
            }
            eventType = parser.next();
        }
        for (String username : usernameList) {
            cob_username.addItem(username);
        }
        for (String group : groupList) {
            cob_group.addItem(group);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cb_isMindGroup;
    private javax.swing.JCheckBox cb_isMindUsername;
    private javax.swing.JComboBox cob_group;
    private javax.swing.JComboBox cob_username;
    private javax.swing.JButton exit;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login;
    // End of variables declaration//GEN-END:variables

    private Document readXml() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        SAXReader sax = new SAXReader();
        Document doc = null;
        try {
            doc = sax.read(new File("src/res/data/username_group.xml"));
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    private void write2XML(Document doc) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        OutputFormat opf = OutputFormat.createPrettyPrint();
        OutputStream os = new FileOutputStream("src/res/data/username_group.xml");
        XMLWriter xMLWriter = new XMLWriter(os, opf);
        xMLWriter.write(doc);
        xMLWriter.close();
    }
}
