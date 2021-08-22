package Item;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class History {
    String name;
    String operator;
    java.sql.Timestamp date;

    public History(){

    }

    public History(String name, String operator, Timestamp date) {
        this.name = name;
        this.operator = operator;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dat = dateFormat.format(date);
        return dat;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "History{" +
                "name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", date=" + getDate() +
                '}';
    }
}
