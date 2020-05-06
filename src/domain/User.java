package domain;

import domain.other.Sex;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 用户实体类
 */
@Component
public class User  implements Serializable {
    //用户ID
    private int user_id;
    //用户名
    private String user_name;
    //密码
    private String password;
    //性别
    private Sex sex;
    //年龄
    private int age;
    //邮箱
    private String email;
    //手机号
    private String phone;
    //激活状态
    private int status;
    //激活码
    private String activecode;
    //无参构造
    public User() {
    }
    //用户名密码构造
    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
    //全参构造
    public User(int user_id, String user_name, String password, Sex sex, int age, String email, String phone) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.password = password;
        this.sex = sex;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", activecode='" + activecode + '\'' +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getActivecode() {
        return activecode;
    }

    public void setActivecode(String activecode) {
        this.activecode = activecode;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
