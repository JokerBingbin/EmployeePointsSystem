package admi;

public class User {
    String username;
    String password;
    int power;                                         //权限，0为管理员，1为普通用户
    String name;
    String subject;
    public User(){

    }
    public User(String un,String pw,String name,String subject,int power){
        this.username = un;
        this.password = pw;
        this.name = name;
        this.subject = subject;
        this.power = power;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
