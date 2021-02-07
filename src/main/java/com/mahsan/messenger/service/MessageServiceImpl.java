package com.mahsan.messenger.service;

import com.mahsan.messenger.entity.Message;
import com.mahsan.messenger.entity.User;
import com.mahsan.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

    private  UserService userService;
    private MessageRepository messageRepository;


    @Autowired
    public MessageServiceImpl(UserService userService, MessageRepository messageRepository) {
        this.userService = userService;
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveNewMessage(Message message) {

        User contact=userService.getUserById(message.getToId());
        User owner=userService.getUserById(message.getFromId());

        message.setFrom(owner);
        message.setTo(contact);

        Message savedMessage=messageRepository.save(message);


        return savedMessage;
    }

    @Override
    public Page<Message> getMessageByContact(int page,int size,Long fromId, Long toId) {

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Message> messages=messageRepository.findAllByFrom_IdAndTo_Id(pageable,fromId,toId);


        return messages;
    }

    @Override
    public Page<Message> getMessageByContactPhone(int page, int size, String fromPhone, String toPhone) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "id");
        Page<Message> messages=messageRepository.findAllByFrom_PhoneAndTo_Phone(pageable,fromPhone,toPhone);


        return messages;    }
}
