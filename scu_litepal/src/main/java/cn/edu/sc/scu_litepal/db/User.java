package cn.edu.sc.scu_litepal.db;

import androidx.annotation.NonNull;

import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {
    private String username;
    private int age;
    private String type;

    public User(String username, int age, String type) {
        this.username = username;
        this.age = age;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @NonNull
    @Override
    public String toString() {
        return "name:"+username+",type"+type+",age:"+age;
    }
}
