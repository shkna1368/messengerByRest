package com.mahsan.messenger.service;

import com.mahsan.messenger.entity.User;
import com.mahsan.messenger.exception.ConflictException;
import com.mahsan.messenger.exception.NotFoundException;
import com.mahsan.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
 private UserRepository userRepository;


 @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository=userRepository;


    }

    @Override
    public User addNewUser(User user) {
        Optional<User> optionalUserByUserName=userRepository.findByUserName(user.getUserName());

        if (optionalUserByUserName.isPresent()){
            throw new ConflictException("This userName already exist");
        }
        Optional<User> optionalUserByPhone=userRepository.findByUserName(user.getPhone());

        if (optionalUserByPhone.isPresent()){
            throw new ConflictException("This phone already exist");
        }

        User savesUser=userRepository.save(user);


        return savesUser;
    }

    @Override
    public User getUserById(Long id) {

        Optional<User> optionalUserByUserName=userRepository.findById(id);

        if (!optionalUserByUserName.isPresent()){
            throw new NotFoundException("This user not exist");
        }

        return optionalUserByUserName.get();

    }

    @Override
    public User findByUserName(String userName) {

        Optional<User> optionalUserByUserName=userRepository.findByUserName(userName);

        if (!optionalUserByUserName.isPresent()){
            throw new NotFoundException("This userName not exist");
        }

        return optionalUserByUserName.get();


 }

    @Override
    public User findByPhone(String phone) {
        Optional<User> optionalUserByPhone=userRepository.findByPhone(phone);

        if (!optionalUserByPhone.isPresent()){
            throw new NotFoundException("This userName not exist");
        }
        return  optionalUserByPhone.get();
    }

    @Override
    public Page<User> findAll(int size, int page) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");
       Page<User> users=userRepository.findAll(pageable);
      return users;

    }
}
