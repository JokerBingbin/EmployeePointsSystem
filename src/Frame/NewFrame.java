package Frame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import Employee.Emp;
import Item.Dep;
import JDBC.JDBCUtils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class NewFrame extends JFrame {
    JFrame main = null;
    JButton em_b;
    JButton ru_b;
    JPanel main_p;
    JPanel em_p;
    JPanel ru_p;
    public NewFrame(){
        main = new JFrame("先声教育员工积分系统");
        main.setSize(1000,800);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("logo/2.png");
        main.setIconImage(icon.getImage());
        main.setLocationRelativeTo(null);
        main_p = new JPanel();
        Layout(main_p);
        main.add(main_p);
        main.setVisible(true);
    }

    @Test
    public void test(){
        main = new JFrame("先声教育员工积分系统");
        main.setSize(1000,800);
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon("logo/2.png");
        main.setIconImage(icon.getImage());
        main.setLocationRelativeTo(null);
        main_p = new JPanel();
        Layout(main_p);
        main.add(main_p);
        main.setVisible(true);
    }
    private void Layout(JPanel jp) {
        main_p.setLayout(null);
        main_p.setBounds(0,0,1000,800);
        em_b = new JButton("员工管理");
        ru_b = new JButton("规则管理");
        em_b.setBounds(20,10,150,50);
        ru_b.setBounds(240,10,150,50);
        jp.add(em_b);
        jp.add(ru_b);
        JPanel son_jp = new JPanel();
        son_jp.setBounds(0,60,1000,740);
        jp.add(son_jp);
        em_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                SetEm(son_jp);
            }
        });
        ru_b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                son_jp.removeAll();
                son_jp.repaint();
                SetRu(son_jp);
            }
        });

        jp.setVisible(true);

    }

    private void SetEm(JPanel jp){
        jp.setLayout(null);
        JComboBox dep_box = new JComboBox();
        add_dep(dep_box);
        JComboBox sort_box = new JComboBox();
        sort_box.addItem("倒序");
        sort_box.addItem("升序");
        dep_box.setBounds(20,10,150,50);
        sort_box.setBounds(240,10,150,50);
        jp.add(dep_box);
        jp.add(sort_box);
        JScrollPane jsp = new JScrollPane();
        jsp.setBounds(20,20,1100,500);
        jp.add(jsp);

        jp.setVisible(true);


    }
    private void SetRu(JPanel jp){

    }

    private void add_dep(JComboBox dep_box){
        dep_box.addItem("全部");
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from dep";
        List<Dep> Deps = template.query(sql, new BeanPropertyRowMapper<Dep>(Dep.class));
        for (int i = 0; i < Deps.size(); i++) {
            Dep dep =  Deps.get(i);
            dep_box.addItem(dep.getName());
        }
    }

    private void Set_table(JScrollPane jsp,JComboBox dep_box,JComboBox sort_box){
        String dep = dep_box.getSelectedItem().toString();
        String sort = sort_box.getSelectedItem().toString();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        String title[] = new String[4];
        title[0] = " ";
        title[1] = "姓名";
        title[2] = "部门";
        title[3] = "积分";
        model.addColumn(title[1]);
        model.addColumn(title[2]);
        model.addColumn(title[3]);
        model.addRow(title);
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
        String sql = "select * from employee";
        List<Emp> emps = template.query(sql, new BeanPropertyRowMapper<Emp>(Emp.class));
        for (int i = 0; i < emps.size(); i++) {
            Emp emp =  emps.get(i);

        }


    }
}
