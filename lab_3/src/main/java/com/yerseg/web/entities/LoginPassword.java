package com.yerseg.web.entities;

import javax.persistence.*;

@Entity
@Table(name = "loginpassword")
public class LoginPassword {
    @Id
    private String login;
    private String hashedPassword;

    public LoginPassword() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public String getLogin() {
        return this.login;
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public String toString(){
        return login;
    }
}


