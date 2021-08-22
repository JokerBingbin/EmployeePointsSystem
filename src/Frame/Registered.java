package Frame;

import JDBC.JDBCUtils.JDBCUtils;
import admin_word.Word;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Registered {
    Word w = new Word();
    String Adi_password = w.Get();
    public  Registered(){
        JFrame reg = new JFrame("注册");
        reg.setSize(400,400);
        reg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        reg.setLocationRelativeTo(null);
        JPanel PanelReg = new JPanel();
        PanelReg.setLayout(null);
        JLabel L_username = new JLabel("用户名");
        JLabel L_password = new JLabel("密码");
        JLabel L_Repeat = new JLabel("重复密码");
        JLabel L_name = new JLabel("姓名");
        JLabel L_subject = new JLabel("职务");
        JLabel L_power = new JLabel("管理员注册密码(选填)");

        JTextField T_username = new JTextField();
        JPasswordField T_password = new JPasswordField();
        JPasswordField T_repeat = new JPasswordField();
        JTextField T_name = new JTextField();
        JComboBox C_subject = new JComboBox();
        C_subject.addItem("");
        C_subject.addItem("语文");
        C_subject.addItem("数学");
        C_subject.addItem("英语");
        C_subject.addItem("物理");
        C_subject.addItem("化学");
        C_subject.addItem("行政");
        JPasswordField T_power = new JPasswordField();

        JButton jb  = new JButton("确定");

        PanelReg.add(L_name);
        PanelReg.add(L_username);
        PanelReg.add(L_password);
        PanelReg.add(L_power);
        PanelReg.add(L_Repeat);
        PanelReg.add(L_subject);
        PanelReg.add(C_subject);
        PanelReg.add(T_name);
        PanelReg.add(T_username);
        PanelReg.add(T_password);
        PanelReg.add(T_power);
        PanelReg.add(T_repeat);
        PanelReg.add(jb);

        L_username.setBounds(30,40,80,25);
        T_username.setBounds(200,40,160,25);
        L_password.setBounds(30,70,80,25);
        T_password.setBounds(200,70,160,25);
        L_Repeat.setBounds(30,100,80,25);
        T_repeat.setBounds(200,100,160,25);
        L_name.setBounds(30,130,80,25);
        T_name.setBounds(200,130,160,25);
        L_subject.setBounds(30,160,80,25);
        C_subject.setBounds(200,160,100,25);
        L_power.setBounds(30,190,120,25);
        T_power.setBounds(200,190,160,25);
        jb.setBounds(175,230,75,50);
        reg.add(PanelReg);
        reg.setVisible(true);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = T_username.getText().trim();
                String password = String.valueOf(T_password.getPassword());
                String repeat = String.valueOf(T_repeat.getPassword());
                String name = T_name.getText();
                String subject = C_subject.getSelectedItem().toString();
                String power = String.valueOf(T_power.getPassword());
                if(username.equals("") || password.equals("") || repeat.equals("")|| name.equals("")|| subject.equals("")){
                    JDialog jdialog = new JDialog(reg,"注册失败",true);
                    jdialog.setSize(200,200);
                    jdialog.setLocationRelativeTo(null);
                    JLabel False = new JLabel("信息不完整，请填写完整");
                    False.setBounds(30,60,150,25);
                    JPanel jp2 = new JPanel();
                    JButton B = new JButton("确定");
                    B.setBounds(50,130,100,30);
                    jp2.setLayout(null);
                    jp2.setSize(200,200);
                    jp2.add(False);
                    jp2.add(B);
                    jdialog.add(jp2);
                    B.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jdialog.dispose();
                        }
                    });
                    jdialog.setVisible(true);



                }else if(password.equals(repeat) == false){
                    JDialog jdialog = new JDialog(reg,"注册失败",true);
                    jdialog.setLayout(null);
                    JButton B = new JButton("确定");
                    B.setBounds(50,130,100,30);
                    JLabel False = new JLabel("两次密码不一样 请重新输入");
                    jdialog.setSize(200,200);
                    jdialog.setLocationRelativeTo(null);
                    jdialog.add(False);
                    jdialog.add(B);
                    False.setBounds(30,60,150,25);
                    B.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jdialog.dispose();
                        }
                    });
                    jdialog.setVisible(true);
                }else{
                    Connection conn = null;
                    PreparedStatement pstmt = null;
                    int p;
                    if(power.equals(Adi_password))
                        p = 0;
                    else p = 1;

                    try {
                        conn = JDBCUtils.getConnection();
                        String sql = "insert into user values(?,?,?,?,?)";
                        pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1,username);
                        pstmt.setString(2,password);
                        pstmt.setString(3,name);
                        pstmt.setString(4,subject);
                        pstmt.setInt(5,p);
                        pstmt.executeUpdate();
                        JDialog jdialog = new JDialog(reg,"恭喜",true);
                        JLabel T  = new JLabel("注册成功！");
                        JButton B = new JButton("返回登录");
                        jdialog.setLayout(null);
                        jdialog.setLocationRelativeTo(null);
                        jdialog.setSize(150,150);
                        jdialog.add(T);
                        jdialog.add(B);
                        T.setBounds(50,30,100,25);
                        B.setBounds(30,70,100,25);
                        B.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jdialog.dispose();
                                reg.dispose();
                            }
                        });
                        jdialog.setVisible(true);


                    } catch (Exception Exception) {
                        Exception.printStackTrace();
                    }
                    finally {
                        JDBCUtils.close(pstmt,conn);
                    }


                }


            }
        });
    }

}
