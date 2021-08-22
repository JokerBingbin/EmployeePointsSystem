package admin_word;

import JDBC.JDBCUtils.JDBCUtils;
import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.*;
import java.util.List;

public class Word {
    String password;
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    public Word(){

    }


    public void Set(String password) throws Exception{
        int[] a = new int[password.length()+1];
        char[] c = new char[password.length()+1];
        c = password.toCharArray();
        for(int i = 0;i<password.length();i++){
            a[i] = (int)c[i];
            if(i%2==1)
                a[i] -= 4;
            else a[i] += 4;
            c[i] = (char)a[i];
        }
        String pw = new String(c);
        String sql = "update ad_pw set pw = ?";
        template.update(sql,pw);
    }
    public String Get(){
            String sql = "select pw from ad_pw";
            List<String> strings = template.queryForList(sql,String.class);
            password = strings.get(0);
            int[] a = new int[password.length()+1];
            char[] c = password.toCharArray();
            for(int i = 0;i<password.length();i++){
                a[i] = (int)c[i];
                if(i%2==0)
                    a[i] -= 4;
                else a[i] += 4;
                c[i] = (char)a[i];
            }
            String pw = new String(c);
            return pw;

    }
}
