package com.mahsan.messenger.service;

import com.mahsan.messenger.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

     User addNewUser(User user);
     User getUserById(Long id);
     User findByUserName(String userName);
     User findByPhone(String phone);
     Page<User> findAll(int count, int page);
}
