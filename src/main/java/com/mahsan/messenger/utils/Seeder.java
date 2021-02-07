package com.mahsan.messenger.utils;


import com.mahsan.messenger.entity.Message;
import com.mahsan.messenger.entity.User;
import com.mahsan.messenger.repository.MessageRepository;
import com.mahsan.messenger.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class Seeder {

    UserRepository userRepository;
    MessageRepository messageRepository;

    @Autowired
    public Seeder(UserRepository userRepository,MessageRepository messageRepository) {

       this.userRepository =userRepository;
       this.messageRepository =messageRepository;
    }



    public void addUser(){
String phone1="09048875060";
String user1="shabab";
String phone2="09048875059";
String user2="mahsan";

        User u1=new User();
        u1.setPhone(phone1);
        u1.setUserName(user1);

         User u2=new User();
        u2.setPhone(phone2);
        u2.setUserName(user2);

    Optional<User> optionalUser1= userRepository.findByUserName(user1);
    Optional<User> optionalUser2= userRepository.findByUserName(user2);

    if(!optionalUser1.isPresent()){
    User savedUser1=    userRepository.save(u1);
        if(!optionalUser2.isPresent()){
            User savedUser2=    userRepository.save(u2);

            addMessage(savedUser1,savedUser2);
            addMessage(savedUser2,savedUser1);

        }


    }







        }


private void addMessage(User from,User to){

    Message message=new Message();
    message.setFrom(from);
    message.setTo(to);
    message.setText("message1 from "+ from.getUserName()+" to "+to.getUserName());
    messageRepository.save(message);

    Message message2=new Message();
    message2.setFrom(from);
    message2.setTo(to);
    message2.setText("message2 from "+ from.getUserName()+" to "+to.getUserName());
    messageRepository.save(message2);




}






    }

