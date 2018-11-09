package thomasvalentini.androiduiandlogin.sweng888.psu.edu.androiduiandlogin_thomasvalentini.model.entity;

import java.io.Serializable;
import java.util.Date;

public class UserProfile implements Serializable {

    //Model Variables
    private long ID;
    private String name;
    private String surname;
    private String username;
    private String birthday;
    private String mobile_phone;
    private String email;
    private String password;
    private String provider;

    public UserProfile(long ID, String name, String surname, String username, String birthday, String mobile_phone, String email, String password, String provider) {
        this.ID = ID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.birthday = birthday;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.password = password;
        this.provider = provider;
    }

    ;

    public UserProfile(String name, String surname, String username, String birthday, String mobile_phone, String email, String password, String provider) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.birthday = birthday;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.password = password;
        this.provider = provider;
    }

    public UserProfile(String email, String provider) {
        this.email = email;
        this.provider = provider;
    }

    public UserProfile(String name, String surname, String username, String birthday, String mobile_phone, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.birthday = birthday;
        this.mobile_phone = mobile_phone;
        this.email = email;
        this.password = password;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getMobile_phone() {
        return mobile_phone;
    }

    public void setMobile_phone(String mobile_phone) {
        this.mobile_phone = mobile_phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}

