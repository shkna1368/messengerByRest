package com.mahsan.messenger.repository;


import com.mahsan.messenger.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    Optional<User> findById(Long id);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrPhone(String userName,String phone);
    Optional<User> findByPhone(String phone);
    Page<User> findAll(Pageable pageable);


}
