package com.mahsan.messenger.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "t_user")
public class User  extends BaseEntity {
    private String userName;
    private String phone;


    @OneToMany(mappedBy ="to" ,fetch = FetchType.LAZY)
    private Set<Message> recivedMessage;

    @OneToMany(mappedBy ="from" ,fetch = FetchType.LAZY)
    private Set<Message> senderMessage;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Message> getRecivedMessage() {
        return recivedMessage;
    }

    public void setRecivedMessage(Set<Message> recivedMessage) {
        this.recivedMessage = recivedMessage;
    }

    public Set<Message> getSenderMessage() {
        return senderMessage;
    }

    public void setSenderMessage(Set<Message> senderMessage) {
        this.senderMessage = senderMessage;
    }
}
