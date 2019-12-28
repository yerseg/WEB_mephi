package com.yerseg.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String body;

    @JoinColumn
    @OneToOne
    private Users sender;

    @JoinColumn
    @OneToOne
    private Users receiver;

    @Column
    private Date sendAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public Users getSender() { return sender; }
    public void setSender(Users sender) { this.sender = sender; }

    public Users getReceiver() { return receiver; }
    public void setReceiver(Users receiver) { this.receiver = receiver; }

    public Date getSendAt() { return sendAt; }
    public void setSendAt(Date sendAt) { this.sendAt = sendAt; }






}
