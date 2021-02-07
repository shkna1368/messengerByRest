package com.mahsan.messenger.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_message")
public class Message extends BaseEntity {


   @ManyToOne (fetch = FetchType.LAZY)
   @JoinColumn(name="from_user_id", nullable=false)

   private User from;


    @ManyToOne (fetch = FetchType.LAZY)

    @JoinColumn(name="to_user_id", nullable=false)
    private User to;

    private String text;


    @Transient
    private Long toId ;

     @Transient
    private Long fromId ;



    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }


}
