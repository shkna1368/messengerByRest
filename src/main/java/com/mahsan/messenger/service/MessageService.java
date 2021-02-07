package com.mahsan.messenger.service;

import com.mahsan.messenger.entity.Message;
import org.springframework.data.domain.Page;

public interface MessageService {

    Message saveNewMessage(Message message);
    Page<Message> getMessageByContact(int page,int size,Long fromId,Long toId);
    Page<Message> getMessageByContactPhone(int page,int size,String fromPhone,String toPhone);


}
