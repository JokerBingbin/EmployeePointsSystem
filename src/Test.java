import JDBC.JDBCUtils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Test {
    String s1 = "zhangruizhenshuai";
    String s2;

    @org.junit.Test
    public void test(){
        int[] a = new int[s1.length()+1];
        char[] c = s1.toCharArray();
        for(int i = 0;i<s1.length();i++){
            a[i] = (int)c[i];
            if(i%2==1)
                a[i] -= 4;
            else a[i] += 4;
            c[i] = (char)a[i];

        }
        s1 = String.valueOf(c);
        System.out.println(s1);
    }
}
