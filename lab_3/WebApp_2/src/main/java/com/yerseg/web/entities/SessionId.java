package com.yerseg.web.entities;

import javax.persistence.*;

@Entity
@Table(name = "sessionid")
public class SessionId {
    @Id
    private String login;
    private String uuid;

    public SessionId() {
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setUuid(String hashOfSession) {
        this.uuid = hashOfSession;
    }

    public String getLogin() {
        return this.login;
    }

    public String getUuid() {
        return this.uuid;
    }
}

