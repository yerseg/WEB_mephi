package com.yerseg.web;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "my_table")
public class User {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String login;
    private String hashedPassword;

    public User(String login, String hashedPassword)
    {
        this.login = login;
        this.hashedPassword = hashedPassword;
    }

    public User() {

    }
}


