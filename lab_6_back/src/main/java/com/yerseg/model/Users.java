package com.yerseg.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Date createdAt;

    public Users() {
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
        this.createdAt = new Date();
    }

    public Users(String login, String password, Date createdAt) {
        this.email = login;
        this.password = password;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date date) { this.createdAt = date; }
}

