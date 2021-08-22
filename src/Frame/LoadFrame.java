package Frame;

import JDBC.JDBCUtils.JDBCUtils;
import admi.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.List;

public class LoadFrame extends JFrame {
    static JFrame jf;
    JPanel panel;
    static JTextField userText;
    static JPasswordField passwordText;
    static JButton login;
    static JButton registered;
    static URL url1 = LoadFrame.class.getResource("/logo/1.png");
    static URL url2 = LoadFrame.class.getResource("/logo/2.png");
    String moren = "zhangruizhenshuai";
    public LoadFrame() {
        init_table();
        jf = new JFrame("先声教育员工积分系统");
        jf.setSize(500, 400);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(url2);
        jf.setIconImage(icon.getImage());
        jf.setLocationRelativeTo(null);                                                 //居中
        panel = new JPanel();
        jf.add(panel);
        placeComponent(panel);

        jf.setVisible(true);

    }

    public void init_table(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "create table if not exists ad_pw (pw varchar(20))";
        template.update(sql);
        sql = "select pw from ad_pw";
        List<String> list = template.queryForList(sql, String.class);
        if(list.size()==0) {
            sql = "insert into ad_pw values (?)";
            int[] a = new int[moren.length()+1];
            char[] c = new char[moren.length()+1];
            c = moren.toCharArray();
            for(int i = 0;i<moren.length();i++){
                a[i] = (int)c[i];
                if(i%2==1)
                    a[i] -= 4;
                else a[i] += 4;
                c[i] = (char)a[i];
            }
            String pw = String.valueOf(c);
            template.update(sql,pw);

        }
        sql = "create table if not exists user(username varchar(32),password varchar(32),name varchar(10),subject varchar(4),power int);";
        template.update(sql);
    }
    private static void placeComponent(JPanel panel) {
        panel.setLayout(null);
        ImageIcon img = new ImageIcon(url1);
        img.setImage(img.getImage().getScaledInstance(250, 70, 250));
        JLabel label = new JLabel();
        label.setIcon(img);
        label.setBounds(110, 70, 250, 100);
        panel.add(label);


        JLabel user = new JLabel("用户名:");
        user.setBounds(110, 195, 80, 25);
        panel.add(user);

        userText = new JTextField(20);
        userText.setBounds(200, 195, 165, 25);
        panel.add(userText);

        JLabel password = new JLabel("密码：");
        password.setBounds(110, 225, 80, 25);
        panel.add(password);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(200, 225, 165, 25);
        panel.add(passwordText);

        login = new JButton("登录");
        login.setBounds(110, 280, 80, 25);
        panel.add(login);

        registered = new JButton("注册");
        registered.setBounds(270, 280, 80, 25);
        panel.add(registered);

        userText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    DoLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                return;
            }
        });
        passwordText.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                return;
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    DoLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                return;
            }
        });
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DoLogin();
            }
        });


        registered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registered r = new Registered();

            }
        });

    }

    private static void DoLogin() {
        String username = userText.getText();
        String password = String.valueOf(passwordText.getPassword());
        if (username == "" || password == "") {
            JDialog jdialog = new JDialog(jf, "登录失败", true);
            jdialog.setLayout(null);
            JButton B = new JButton("确定");
            B.setBounds(50, 130, 100, 30);
            JLabel False = new JLabel("用户名和密码不能为空！");
            jdialog.setSize(200, 200);
            jdialog.setLocationRelativeTo(null);
            jdialog.add(False);
            jdialog.add(B);
            False.setBounds(30, 60, 150, 25);

            B.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jdialog.dispose();
                }
            });
            jdialog.setVisible(true);
        }
        /*Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from user where username = ? and password = ? ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();
            if(!rs.next()){
                JDialog jdialog = new JDialog(jf,"登录失败",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);
                JLabel False = new JLabel("用户名或密码错误");
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
                System.out.println("登录成功");
                jf.dispose();
                MainFrame MF = new MainFrame();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }  finally {
            JDBCUtils.close(rs,pstmt,conn);
        }*/
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from user where username = ? and password = ? ";
        String user_name = null;
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        if(users.size()==0){
            JDialog jdialog = new JDialog(jf,"登录失败",true);
            jdialog.setLayout(null);
            JButton B = new JButton("确定");
            B.setBounds(50,130,100,30);
            JLabel False = new JLabel("用户名或密码错误");
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
        }
        else {
            int User_power = 0;
            MainFrame MF;
            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                user_name = user.getName();
                User_power = user.getPower();
            }
            jf.dispose();
            if(User_power == 0)
                MF = new MainFrame(user_name);
            else{
                System.exit(0);
            }


        }

    }
}

