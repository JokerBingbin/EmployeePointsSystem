package Frame;

import Employee.Emp;
import Item.Dep;
import Item.History;
import Item.Rule;
import JDBC.JDBCUtils.JDBCUtils;
import Poi_Utils.Ex;
import admin_word.Word;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Date;
import java.util.List;

public class MainFrame extends JFrame {
    JFrame main;
    JButton b1;
    JButton b2;
    JButton b3;
    JButton b4;
    JButton b5;
    ImageIcon img;
    Date date = new Date();
    String year = String.format("%tY",date);
    String month = String.format("%tm",date);
    String day = String.format("%td",date);
    String now_date = String.format("%tF",date);
    String now_moment = String.format("%tD",date);
    String Emp_file = "emp_"+year+"_"+month;
    String his = "His_"+year+"_"+month;
    String user_name = null;
    String cheat = "上上下下左右左右BABA";
    String Get = "";
    KeyEvent e;
    static URL url2 = LoadFrame.class.getResource("/logo/2.png");
    static URL url1 = LoadFrame.class.getResource("/logo/1.png");
    public MainFrame(String user_name) {
        init_table();
        this.user_name = user_name;
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "CREATE TABLE IF NOT EXISTS "+Emp_file+" SELECT * FROM employee";
        template.update(sql);
        sql = "create table if not exists "+his+" (name varchar(100),operator varchar(10),time datetime)";
        template.update(sql);
        main = new JFrame("先声教育员工积分系统");
        main.setSize(800, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon(url2);
        main.setIconImage(icon.getImage());
        main.setLocationRelativeTo(null);
       /* JLabel jLabel = new JLabel("您好，管理员"+user_name+"!");
        main.add(jLabel);
        jLabel.setBounds(5,5,200,25);*/
        JLabel jLabel1 = new JLabel("当前日期:"+now_date);
        main.add(jLabel1);
        jLabel1.setBounds(650,560,200,25);
        JPanel jp = new JPanel();
        Layout(jp);
        main.add(jp);
        main.setVisible(true);
    }

    public void init_table(){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "create table if not exists employee (name varchar(10),subject varchar(10),score int)";
        template.update(sql);
        sql = "create table if not exists dep (id int primary key auto_increment,name varchar(10))";
        template.update(sql);
        sql = "select name from dep";
        List<String> list = template.queryForList(sql, String.class);
        if(list.size() == 0){
            String sql1 = "insert into dep values (null,?)";
            template.update(sql1,"语文");
            template.update(sql1,"数学");
            template.update(sql1,"英语");
            template.update(sql1,"物理");
            template.update(sql1,"化学");
            template.update(sql1,"咨询");

        }

        sql = "create table if not exists item (name varchar(32),score int)";
        template.update(sql);

    }
    private void Layout(JPanel jp) {
        jp.setLayout(null);
        jp.setSize(800, 600);
        JLabel jLabel1 = new JLabel("当前日期:"+now_date);
        jp.add(jLabel1);
        jLabel1.setBounds(20,540,200,25);
        img = new ImageIcon(url1);
        img.setImage(img.getImage().getScaledInstance(350, 100, 250));
        JLabel jb = new JLabel();
        jb.setIcon(img);
        jb.setBounds(225, 0, 350, 100);
        jp.add(jb);
        b1 = new JButton("奖扣操作");
        b2 = new JButton("查看");
        b3 = new JButton("员工管理");
        b4 = new JButton("规则管理");
        b5 = new JButton("历史记录");
        jp.add(b1);
        jp.add(b2);
        jp.add(b3);
        jp.add(b4);
        jp.add(b5);
        b1.setBounds(50, 150, 200, 50);
        b2.setBounds(50, 225, 200, 50);
        b3.setBounds(50, 300, 200, 50);
        b4.setBounds(50, 375, 200, 50);
        b5.setBounds(50, 450, 200, 50);

        JPanel son_jp = new JPanel();
        jp.add(son_jp);
        son_jp.setBounds(270, 150, 480, 450);


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                Layout_1(son_jp);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                Layout_2(son_jp);
            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                Layout_3(son_jp);
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                Layout_4(son_jp);
            }
        });

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                Layout_5(son_jp);
            }
        });
        jp.setVisible(true);
    }

    private void Layout_1(JPanel jp) {
        jp.setLayout(null);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

        String sql = "select * from "+Emp_file;
        JComboBox box1 = new JComboBox();
        List<Emp> emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (int i = 0; i < emps.size(); i++) {
            Emp emp =  emps.get(i);
            box1.addItem(emp.getName());
        }



        sql = "select * from item";
        JComboBox box2 = new JComboBox();
        List<Rule> items = template.query(sql, new BeanPropertyRowMapper<Rule>(Rule.class));
        for (int i = 0; i < items.size(); i++) {
            Rule item =  items.get(i);
            box2.addItem(item.getName());
        }


        JLabel e_name = new JLabel("员工:");
        JLabel i_name = new JLabel("原因:");

        JButton sure = new JButton("确定");

        jp.add(e_name);
        jp.add(i_name);
        jp.add(sure);
        jp.add(box1);
        jp.add(box2);

        e_name.setBounds(50,60,100,50);
        i_name.setBounds(50,150,100,50);
        box1.setBounds(150,75,200,30);
        box2.setBounds(150,165,200,30);
        sure.setBounds(180,240,100,50);

        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String E_name = box1.getSelectedItem().toString();
                String I_name = box2.getSelectedItem().toString();
                String sql1 = "select score from item where name = ?" ;
                String s = template.queryForObject(sql1, String.class, I_name);
                sql1 = "select score from "+Emp_file+" where name = ?";
                int old = template.queryForObject(sql1,int.class,E_name);
                sql1 = "update "+Emp_file+" set score = ? where name = ?";

                int now = old+Integer.parseInt(s);
                template.update(sql1,now,E_name);
                String V;
                if(Integer.parseInt(s)>0)
                        V = "+"+s;
                else  V = s;
                String name = E_name+"因"+I_name+"而"+V+"分";
                add_his(name);
                JDialog jdialog = new JDialog(main,"修改成功",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);
                JLabel False = new JLabel("原积分: "+old);
                JLabel l2 = new JLabel("积分变化: " + V);
                JLabel l3 = new JLabel("现积分: " + now);
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(l2);
                jdialog.add(l3);
                jdialog.add(B);
                False.setBounds(30,20,150,25);
                l2.setBounds(30,45,150,25);
                l3.setBounds(30,70,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                    }
                });
                jdialog.setVisible(true);
            }
        });
    }

    private void Layout_2(JPanel jp) {
        jp.setLayout(null);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JButton b1 = new JButton("员工积分");
        JButton b2 = new JButton("规则");
        jp.add(b1);
        jp.add(b2);
        JLabel jsp = new JLabel();
        jp.add(jsp);
        b1.setBounds(55,5,100,30);
        b2.setBounds(255,5,100,30);
        jsp.setBounds(0,50,450,300);
        re_em(jsp,template);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsp.removeAll();
                jsp.repaint();
                re_em(jsp,template);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jsp.removeAll();
                jsp.repaint();
                re_su(jsp,template);
            }
        });

    }

    private void Layout_3(JPanel jp) {
        jp.setLayout(null);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JButton create = new JButton("添加");
        JButton delete = new JButton("删除");
        JButton edit = new JButton("修改");
        jp.add(create);
        jp.add(delete);
        jp.add(edit);
        JPanel son_jp = new JPanel();
        jp.add(son_jp);
        create.setBounds(50,20,100,50);
        delete.setBounds(170,20,100,50);
        edit.setBounds(290,20,100,50);
        son_jp.setBounds(50,70,400,300);
        add_em(son_jp,template);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                add_em(son_jp,template);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                del_em(son_jp,template);
            }
        });

        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                edit_em(son_jp,template);
            }
        });

        jp.setVisible(true);
    }

    private void Layout_4(JPanel jp) {
        jp.setLayout(null);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JButton create = new JButton("添加");
        JButton delete = new JButton("删除");
        JButton edit = new JButton("修改");
        jp.add(create);
        jp.add(delete);
        jp.add(edit);
        JPanel son_jp = new JPanel();
        jp.add(son_jp);
        create.setBounds(50,20,100,50);
        delete.setBounds(170,20,100,50);
        edit.setBounds(290,20,100,50);
        son_jp.setBounds(50,70,400,300);
        add_su(son_jp,template);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                add_su(son_jp,template);
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                del_su(son_jp,template);
            }
        });
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                edit_ru(son_jp,template);
            }
        });

        jp.setVisible(true);

    }

    private void Layout_5(JPanel jp) {
        jp.setLayout(null);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        JComboBox jb = new JComboBox();
        add_mon(jb);
        JPanel son = new JPanel();
        jp.add(jb);
        jp.add(son);
        jb.setBounds(180,5,130,25);
        son.setBounds(0,35,480,400);
        String filename = "his_"+jb.getSelectedItem().toString();
        re_hi(son,template,filename,jb);

        jb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String filename2 = "his_"+jb.getSelectedItem().toString();
                son.removeAll();
                son.repaint();
                re_hi(son,template,filename2,jb);
            }
        });






    }

    void add_em(JPanel jp,JdbcTemplate template){
        jp.setLayout(null);
        JTextField e_name_f = new JTextField();
        JComboBox s_name_b = new JComboBox();
        add_dep(s_name_b);
        JLabel e_name_l = new JLabel("员工姓名");
        JLabel s_name_l = new JLabel("员工职务");
        JButton add = new JButton("添加");

        jp.add(e_name_f);
        jp.add(e_name_l);
        jp.add(s_name_b);
        jp.add(s_name_l);
        jp.add(add);
        e_name_f.setBounds(105,35,200,30);
        e_name_l.setBounds(5,20,100,50);
        s_name_b.setBounds(105,115,200,30);
        s_name_l.setBounds(5,110,100,50);
        add.setBounds(110,180,100,50);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "INSERT INTO "+Emp_file+" values(?,?,0)";
                String sql2 = "INSERT INTO employee values(?,?,0)";
                String e_name = e_name_f.getText();
                String s_name = s_name_b.getSelectedItem().toString();
                template.update(sql,e_name,s_name);
                template.update(sql2,e_name,s_name);
                String n = "添加了"+s_name+"的新员工"+e_name;
                add_his(n);
                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("添加成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                        jp.removeAll();
                        jp.repaint();
                        add_em(jp,template);
                    }
                });
                jdialog.setVisible(true);

            }
        });
    }

    void choose(JComboBox jb1,JComboBox jb2,JdbcTemplate template){
        String name = jb1.getSelectedItem().toString();
        String sql = "select * from "+Emp_file+" where subject = ?";
        List<Emp> Emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class),name);
        for (int i = 0; i < Emps.size(); i++) {
            Emp emp =  Emps.get(i);
            jb2.addItem(emp.getName());
        }
    }

    void del_em(JPanel jp,JdbcTemplate template){
        jp.setLayout(null);
        JComboBox e_name_b = new JComboBox();
        JComboBox s_name_b = new JComboBox();
        s_name_b.addItem("全部");
        add_dep(s_name_b);
        choose(s_name_b,e_name_b,template);
        JLabel e_name_l = new JLabel("员工姓名");
        JLabel s_name_l = new JLabel("员工职务");
        JButton del = new JButton("删除");

        jp.add(e_name_b);
        jp.add(e_name_l);
        jp.add(s_name_b);
        jp.add(s_name_l);
        jp.add(del);

        e_name_b.setBounds(105,115,200,30);
        e_name_l.setBounds(5,110,100,50);
        s_name_b.setBounds(105,35,200,30);
        s_name_l.setBounds(5,20,100,50);
        del.setBounds(110,180,100,50);

        s_name_b.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                e_name_b.removeAllItems();
                choose(s_name_b,e_name_b,template);
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "DELETE FROM employee where name = ?";
                String sql2 = "DELETE FROM "+Emp_file+" where name = ?";
                String e_name = e_name_b.getSelectedItem().toString();
                //String s_name = s_name_b.getSelectedItem().toString();
                template.update(sql,e_name);
                template.update(sql2,e_name);
                String n = "删除了员工"+e_name;
                add_his(n);
                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("删除成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                        jp.removeAll();
                        jp.repaint();
                        del_em(jp,template);
                    }
                });
                jdialog.setVisible(true);

            }
        });
    }

    void edit_em(JPanel jp,JdbcTemplate template){
        Get = "";
        jp.setLayout(null);
        JComboBox jb = new JComboBox();
        add_em(jb);
        jp.add(jb);
        jb.setBounds(65,20,200,25);

        JPanel son = new JPanel();
        jp.add(son);
        son.setBounds(0,50,400,250);
        String name = jb.getSelectedItem().toString();

        edit(son,template,name,jp);


        jb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name2 = jb.getSelectedItem().toString();
                son.removeAll();
                son.repaint();
                edit(son,template,name2,jp);
            }
        });
    }

    void edit (JPanel jp,JdbcTemplate template,String name,JPanel father){
        String sql = "select * from "+Emp_file+" where name = ?";
        List<Emp> emps = template.query(sql,new BeanPropertyRowMapper<>(Emp.class),name);
        Emp emp = emps.get(0);
        JTextField jt1 = new JTextField(emp.getName());
        JComboBox jt2 = new JComboBox();
        jt2.addItem(emp.getSubject());
        add_dep(jt2);
        JTextField jt3 = new JTextField(String.valueOf(emp.getScore()));
        JTextField jt4 = new JTextField();
        JLabel jl1 = new JLabel("姓名");
        JLabel jl2 = new JLabel("部门");
        JLabel jl3 = new JLabel("积分");
        JLabel jl4 = new JLabel("修改原因");
        JButton jb = new JButton("确定修改");

        jp.add(jt1);
        jp.add(jt2);
        jp.add(jt3);
        jp.add(jt4);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jl3);
        jp.add(jl4);
        jp.add(jb);

        jl1.setBounds(5,10,50,25);
        jl2.setBounds(5,60,50,25);
        jl3.setBounds(5,110,50,25);
        jl4.setBounds(5,160,50,25);
        jt1.setBounds(65,10,200,25);
        jt2.setBounds(65,60,200,25);
        jt3.setBounds(65,110,200,25);
        jt4.setBounds(65,160,200,25);
        jb.setBounds(100,200,100,25);

        jb.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_name = jt1.getText().toString();
                String new_sub = jt2.getSelectedItem().toString();
                int new_score = Integer.parseInt(jt3.getText().toString());
                String reason = jt4.getText().toString();
                if (reason.equals("")) {
                    JDialog jdialog = new JDialog(main, "修改失败", true);
                    jdialog.setSize(200, 200);
                    jdialog.setLocationRelativeTo(null);
                    JLabel False = new JLabel("原因不能为空");
                    False.setBounds(30, 60, 150, 25);
                    JPanel jp2 = new JPanel();
                    JButton B = new JButton("确定");
                    B.setBounds(50, 130, 100, 30);
                    jp2.setLayout(null);
                    jp2.setSize(200, 200);
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
                } else {
                    boolean N = new_name.equals(emp.getName());
                    boolean S = new_sub.equals(emp.getSubject());
                    boolean s;
                    if (new_score == emp.getScore())
                        s = true;
                    else s = false;

                    String sql = "update " + Emp_file + " set name = ?,subject = ?,score = ? where name = ?";
                    template.update(sql, new_name, new_sub, new_score, emp.getName());
                    String sql2 = "update employee set name = ?,subject = ? where name = ?";
                    template.update(sql2, new_name, new_sub,emp.getName());

                    if (!N) {
                        String op = "因" + reason + "将" + emp.getName() + "改成了" + new_name;
                        add_his(op);
                    }
                    if (!S) {
                        String op = "因" + reason + "将" +new_name + "的部门从" + emp.getSubject() + "改成了" + new_sub;
                        add_his(op);
                    }
                    if (!s) {
                        String op = "因" + reason + "将" +new_name + "的积分从" + emp.getScore() + "改成了" + new_score;
                        add_his(op);
                    }

                    JDialog jdialog = new JDialog(main,"恭喜",true);
                    jdialog.setLayout(null);
                    JButton B = new JButton("确定");
                    B.setBounds(50,130,100,30);

                    JLabel False = new JLabel("修改成功！");
                    jdialog.setSize(200,200);
                    jdialog.setLocationRelativeTo(null);
                    jdialog.add(False);
                    jdialog.add(B);
                    False.setBounds(65,60,150,25);
                    B.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            jdialog.dispose();
                            father.removeAll();
                            father.repaint();
                            edit_em(father, template);
                        }
                    });
                    jdialog.setVisible(true);

                }
            }
        });

        jt4.addKeyListener(new KeyAdapter() {


            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(Get.length()>100)
                    Get = "";
                super.keyPressed(e);
                String temp = KeyEvent.getKeyText(e.getKeyCode());
                if(temp.equals("向上箭头"))
                    temp = "上";
                if(temp.equals("向下箭头"))
                    temp = "下";
                if(temp.equals("向左箭头"))
                    temp = "左";
                if(temp.equals("向右箭头"))
                    temp = "右";
                Get += temp;
                if(Get.contains(cheat)){
                    edit_adpw();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
            }
        });

    }
    void edit_adpw(){
        JFrame new_f = new JFrame("修改");
        new_f.setSize(400, 300);
        new_f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        new_f.setLocationRelativeTo(null);
        JPanel jp = new JPanel();
        new_f.add(jp);
        jp.setBounds(0,0,400,300);
        jp.setLayout(null);

        JPasswordField old = new JPasswordField();
        JPasswordField new_pw = new JPasswordField();
        JPasswordField new_pw2 = new JPasswordField();
        JButton jb = new JButton("确定");

        jp.add(old);
        jp.add(new_pw);
        jp.add(new_pw2);
        jp.add(jb);

        old.setBounds(100,55,200,25);
        new_pw.setBounds(100,90,200,25);
        new_pw2.setBounds(100,125,200,25);
        jb.setBounds(150,200,75,25);


        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opw = String.valueOf(old.getPassword());
                String npw = String.valueOf(new_pw.getPassword());
                String npw2 = String.valueOf(new_pw2.getPassword());

                Word w = new Word();
                if(!opw.equals(w.Get())){
                    JDialog jdialog = new JDialog(new_f, "修改失败", true);
                    jdialog.setLayout(null);
                    JButton B = new JButton("确定");
                    B.setBounds(100, 130, 100, 30);
                    JLabel False = new JLabel("原密码错误");
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
                }else if(!npw.equals(npw2)){
                    JDialog jdialog = new JDialog(new_f, "修改失败", true);
                    jdialog.setLayout(null);
                    JButton B = new JButton("确定");
                    B.setBounds(80, 130, 100, 30);
                    JLabel False = new JLabel("两次密码不同");
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

                }else{
                    try {
                        w.Set(npw);
                        JDialog jdialog = new JDialog(new_f, "恭喜", true);
                        jdialog.setLayout(null);
                        JButton B = new JButton("确定");
                        B.setBounds(100, 130, 100, 30);
                        JLabel False = new JLabel("修改成功");
                        jdialog.setSize(200, 200);
                        jdialog.setLocationRelativeTo(null);
                        jdialog.add(False);
                        jdialog.add(B);
                        False.setBounds(30, 60, 150, 25);

                        B.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Get = "";
                                jdialog.dispose();
                                new_f.dispose();

                            }
                        });
                        jdialog.setVisible(true);

                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }


                }
            }
        });
        jp.setVisible(true);


        new_f.setVisible(true);


    }
    void edit_ru(JPanel jp,JdbcTemplate template){
        jp.setLayout(null);
        JComboBox jb = new JComboBox();
        add_ru(jb);
        jp.add(jb);
        jb.setBounds(65,20,200,25);

        JPanel son = new JPanel();
        jp.add(son);
        son.setBounds(0,50,400,250);
        String name = jb.getSelectedItem().toString();
        edit_2(son,template,name,jp);

        jb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String name2 = jb.getSelectedItem().toString();
                son.removeAll();
                son.repaint();
                edit_2(son,template,name2,jp);
            }
        });
    }

    void edit_2(JPanel jp,JdbcTemplate template,String name,JPanel father){
        String sql = "select * from item where name = ?";
        List<Rule> rules = template.query(sql,new BeanPropertyRowMapper<>(Rule.class),name);
        Rule rule = rules.get(0);

        JTextField jf1 = new JTextField(rule.getName());
        JTextField jf2 = new JTextField(String.valueOf(rule.getScore()));

        JLabel jl1 = new JLabel("规则名称");
        JLabel jl2 = new JLabel("规则分值");

        JButton jb = new JButton("确定修改");

        jp.add(jf1);
        jp.add(jf2);
        jp.add(jl1);
        jp.add(jl2);
        jp.add(jb);

        jl1.setBounds(5,30,50,25);
        jl2.setBounds(5,130,50,25);

        jf1.setBounds(65,30,200,25);
        jf2.setBounds(65,130,200,25);
        jb.setBounds(100,200,100,25);

        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String new_name = jf1.getText().toString();
                int new_score  = Integer.valueOf(jf2.getText().toString());
                String sql = "update item set name = ?,score = ? where name = ?";
                template.update(sql,new_name,new_score,rule.getName());

                if(!new_name.equals(rule.getName())){
                    String op = "修改了规则名称'" + rule.getName() + "'为'" + new_name +"'";
                    add_his(op);
                }
                if(new_score != rule.getScore()){
                    String op = "修改了规则'"+rule.getName()+"'的分值为"+new_score;
                    add_his(op);
                }

                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("修改成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(65,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                        father.removeAll();
                        father.repaint();
                        edit_ru(father,template);
                    }
                });
                jdialog.setVisible(true);

            }
        });

    }

    void add_su(JPanel jp,JdbcTemplate template){
        jp.setLayout(null);
        JTextField s_name_f = new JTextField();
        JTextField s_score_f = new JTextField();
        JLabel  s_name_l = new JLabel("名称");
        JLabel s_score_l = new JLabel("得分");
        JButton add = new JButton("添加");

        jp.add(s_name_f);
        jp.add(s_name_l);
        jp.add(s_score_f);
        jp.add(s_score_l);
        jp.add(add);

        s_name_f.setBounds(105,35,200,30);
        s_name_l.setBounds(5,20,100,50);
        s_score_f.setBounds(105,115,200,30);
        s_score_l.setBounds(5,110,100,50);
        add.setBounds(110,180,100,50);

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "insert into item values(?,?)";
                String s_name = s_name_f.getText().toString();
                String s_score = s_score_f.getText().toString();
                int score = Integer.parseInt(s_score);
                template.update(sql,s_name,score);
                String n = "添加了新的规则:"+s_name;
                add_his(n);
                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("添加成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                        jp.removeAll();
                        jp.repaint();
                        add_su(jp,template);
                    }
                });
                jdialog.setVisible(true);

            }
        });
    }

    void del_su(JPanel jb,JdbcTemplate template){
        jb.setLayout(null);
        JComboBox s_name_b = new JComboBox();
        JLabel s_name_l = new JLabel("名称");
        JButton del = new JButton("删除");

        jb.add(s_name_b);
        jb.add(s_name_l);
        jb.add(del);
        String sql = "select * from item";
        List<Rule> items = template.query(sql,new BeanPropertyRowMapper<Rule>(Rule.class));
        for (int i = 0; i < items.size(); i++) {
            Rule it =  items.get(i);
            s_name_b.addItem(it.getName());
        }
        s_name_b.setBounds(105,80,200,30);
        s_name_l.setBounds(5,70,100,50);
        del.setBounds(110,180,100,50);

        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql1= "delete from item where name = ?";
                String s_name = s_name_b.getSelectedItem().toString();
                template.update(sql1,s_name);
                String n = "删除了规则:"+s_name;
                add_his(n);
                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("删除成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                        jb.removeAll();
                        jb.repaint();
                        del_su(jb,template);
                    }
                });
                jdialog.setVisible(true);
            }
        });
    }

    void re_em(JLabel jsp,JdbcTemplate template){
        jsp.setLayout(null);
        JComboBox jb1 = new JComboBox();
        JComboBox jb2 = new JComboBox();
        JComboBox jb3 = new JComboBox();
        jb1.addItem("全部");
        add_dep(jb1);

        jb2.addItem("倒序");
        jb2.addItem("升序");

        JButton export = new JButton("导出");
      //  JLabel l1 = new JLabel("排序方式:");
        add_mon(jb3);

        JTable jt = new JTable();
        JScrollPane son = new JScrollPane(jt);
        jsp.add(export);

        jsp.add(jb1);
        jsp.add(jb2);
        jsp.add(jb3);
       // jsp.add(l1);
        jsp.add(son);

      //  l1.setBounds(5,5,70,50);
        jb1.setBounds(145,5,130,25);
        jb2.setBounds(282,5,130,25);
        jb3.setBounds(10,5,130,25);
        son.setBounds(5,35,440,220 );
        export.setBounds(150,270,150,25);

        final String[] rule1 = new String[1];
        final String[] rule2 = new String[1];
        String filename = "emp_" + jb3.getSelectedItem().toString();

        if(jb1.getSelectedItem().toString().equals("全部"))
            rule1[0] = "null";
        else rule1[0] = jb1.getSelectedItem().toString();
        if(jb2.getSelectedItem().toString().equals("升序"))
            rule2[0] = "ASC";
        else rule2[0] = "DESC";
        sort(rule1[0], rule2[0],template,jt,filename);

        jb1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String filename2 = "emp_" + jb3.getSelectedItem().toString();
                if(jb1.getSelectedItem().toString().equals("全部"))
                    rule1[0] = "null";
                else rule1[0] = jb1.getSelectedItem().toString();
                if(jb2.getSelectedItem().toString().equals("升序"))
                    rule2[0] = "ASC";
                else rule2[0] = "DESC";
                jt.removeAll();
                jt.repaint();
                sort(rule1[0], rule2[0],template,jt,filename2);
            }
        });
        jb2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String filename2 = "emp_" + jb3.getSelectedItem().toString();
                if(jb1.getSelectedItem().toString().equals("全部"))
                    rule1[0] = "null";
                else rule1[0] = jb1.getSelectedItem().toString();
                if(jb2.getSelectedItem().toString().equals("升序"))
                    rule2[0] = "ASC";
                else rule2[0] = "DESC";
                jt.removeAll();
                jt.repaint();
                sort(rule1[0], rule2[0],template,jt,filename2);
            }
        });

        jb3.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String filename2 = "emp_" + jb3.getSelectedItem().toString();
                if(jb1.getSelectedItem().toString().equals("全部"))
                    rule1[0] = "null";
                else rule1[0] = jb1.getSelectedItem().toString();
                if(jb2.getSelectedItem().toString().equals("升序"))
                    rule2[0] = "ASC";
                else rule2[0] = "DESC";
                jt.removeAll();
                jt.repaint();
                sort(rule1[0], rule2[0],template,jt,filename2);
            }
        });

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sheetname = "积分表";
                String[] title = {"姓名","部门","积分"};
                String file = "emp_" + jb3.getSelectedItem().toString();
                String sql;
                String[][] content;
                List<Emp> emps;
                if(jb1.getSelectedItem().toString().equals("全部")) {
                    sql = "select * from " + file + " order by score DESC";
                    emps = template.query(sql, new BeanPropertyRowMapper<>(Emp.class));

                }
                else{
                    String su = jb1.getSelectedItem().toString();
                    sql = "select * from " + file +" where subject = ? order by score DESC";
                    emps = template.query(sql,new BeanPropertyRowMapper<>(Emp.class),su);
                    su = change(su);
                    file = su+file;
                }
                content = new String[emps.size()][3];
                for (int i = 0; i < emps.size(); i++) {
                    content[i] = new String[title.length];
                    Emp emp =  emps.get(i);
                    content[i][0] = emp.getName();
                    content[i][1] = emp.getSubject();
                    content[i][2] = String.valueOf(emp.getScore());
                }
                Ex ex = new Ex(title,file,sheetname,content);

                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("导出成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                    }
                });
                jdialog.setVisible(true);


            }
        });

        jsp.setVisible(true);

    }

    void sort(String rule1,String rule2,JdbcTemplate template,JTable jt,String filename){
        DefaultTableModel model = new DefaultTableModel();
        jt.setModel(model);
        String sql;
        List<Emp> emps;
        if(rule1.equals("null")){
            sql = "select * from "+filename+" order by score " + rule2;
            emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        }
        else{
            sql = "select * from "+filename+" where subject = ? order by score " + rule2;
            emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class),rule1);
        }

        int count = emps.size() + 1;
        String title[] = new String[3];
        title[0] = "姓名";
        title[1] = "部门";
        title[2] = "积分";
        model.addColumn(title[0]);
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        String[][] row = new String[count][3];
        for (int i = 0; i < emps.size(); i++) {
            Emp emp =  emps.get(i);
            row[i][0] = emp.getName();
            row[i][1] = emp.getSubject();
            row[i][2] = String.valueOf(emp.getScore());
            model.addRow(row[i]);
        }

    }

    void re_su(JLabel jsp,JdbcTemplate template){
        jsp.setLayout(null);

        JTable jt = new JTable();
        JScrollPane son = new JScrollPane(jt);
        DefaultTableModel model = new DefaultTableModel();
        jt.setModel(model);

        jsp.add(son);
        son.setBounds(5,10,440,240);

        String sql = "select * from item order by score DESC";
        List<Rule> items = template.query(sql, new BeanPropertyRowMapper<Rule>(Rule.class));
        String[] title = new String[2];
        title[0] = "名称";
        title[1] = "分数";
        model.addColumn(title[0]);
        model.addColumn(title[1]);
        String[][] row = new String[items.size()][2];
        for (int i = 0; i < items.size(); i++) {
            Rule item =  items.get(i);
            row[i][0] = item.getName();
            row[i][1] = String.valueOf(item.getScore());

            model.addRow(row[i]);
        }

        jsp.setVisible(true);
    }

    void re_hi(JPanel jp,JdbcTemplate template,String filename,JComboBox jb){
        JTable jt = new JTable();
        JScrollPane jsp = new JScrollPane(jt);
        JButton export = new JButton("导出");
        jp.add(jsp);
        jp.add(export);
        jsp.setBounds(0,0,480,280);
        export.setBounds(185,285,100,25);
        DefaultTableModel model = new DefaultTableModel();
        jt.setModel(model);
        String sql = "select * from "+filename;
        List<History> histories = template.query(sql, new BeanPropertyRowMapper<History>(History.class));
        sql = "select time from "+filename;
        List<String> times = template.queryForList(sql, String.class);
        String title[] = new String[3];
        title[0] = "操作";
        title[1] = "操作人";
        title[2] = "操作时间";
        model.addColumn(title[0]);
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        model.addRow(title);
        int count = histories.size() + 1;
        String[][] row = new String[count][3];
        for (int i = 0; i < histories.size(); i++) {
            History history =  histories.get(i);
            String t = times.get(i);
            row[i][0] = history.getName();
            row[i][1] = history.getOperator();
            row[i][2] = t;
            model.addRow(row[i]);
        }

        export.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String date = jb.getSelectedItem().toString();
                String file = "his_"+date;
                String sql2 = "select * from "+file;
                List<History> histories1 = template.query(sql2, new BeanPropertyRowMapper<>(History.class));
                sql2 = "select time from "+file;
                List<String> times2 = template.queryForList(sql2, String.class);
                String sheetName = "历史操作";
                String[] title = {"操作","操作人","操作时间"};
                String[][] content = new String[histories1.size()][3];
                for (int i = 0; i < histories1.size(); i++) {
                    content[i] = new String[title.length];
                    History history =  histories1.get(i);
                    content[i][1] = history.getOperator();
                    content[i][0] = history.getName();
                    content[i][2] = times2.get(i);
                }
                Ex ex = new Ex(title,file,sheetName,content);
                JDialog jdialog = new JDialog(main,"恭喜",true);
                jdialog.setLayout(null);
                JButton B = new JButton("确定");
                B.setBounds(50,130,100,30);

                JLabel False = new JLabel("导出成功！");
                jdialog.setSize(200,200);
                jdialog.setLocationRelativeTo(null);
                jdialog.add(False);
                jdialog.add(B);
                False.setBounds(70,60,150,25);
                B.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jdialog.dispose();
                    }
                });
                jdialog.setVisible(true);

            }
        });
    }

    private void add_dep(JComboBox dep_box){
        //dep_box.addItem("全部");
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from dep";
        List<Dep> Deps = template.query(sql, new BeanPropertyRowMapper<Dep>(Dep.class));
        for (int i = 0; i < Deps.size(); i++) {
            Dep dep =  Deps.get(i);
            dep_box.addItem(dep.getName());
        }
    }

    private void add_mon(JComboBox box){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "SHOW TABLES LIKE 'emp_20%'; ";
        List<String> list = template.queryForList(sql,String.class);
        String mon;
        for (int i = list.size()-1; i >= 0; i--) {
            String s =  list.get(i);
            mon = s.substring(4);
            box.addItem(mon);
        }
    }

    private void add_his(String name){
        String sql = "insert into "+his+" values (?,?,?)";
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        java.sql.Timestamp time = new java.sql.Timestamp(new Date().getTime());
        template.update(sql,name,this.user_name,time);

    }

    private void add_em(JComboBox box){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select name from employee";
        List<String> names = template.queryForList(sql,String.class);
        for (int i = 0; i < names.size(); i++) {
            String s =  names.get(i);
            box.addItem(s);
        }
    }

    private void add_ru(JComboBox box){
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select name from item";
        List<String> names = template.queryForList(sql,String.class);
        for (int i = 0; i < names.size(); i++) {
            String s =  names.get(i);
            box.addItem(s);
        }
    }

    public String change(String sub){
        if(sub.equals("语文"))
            return "Chinese";
        if(sub.equals("数学"))
            return "Math";
        if(sub.equals("英语"))
            return "Eng";
        if(sub.equals("物理"))
            return "Phy";
        if(sub.equals("化学"))
            return "Che";
        if(sub.equals("咨询"))
            return "admin";
        return "";
    }
}


