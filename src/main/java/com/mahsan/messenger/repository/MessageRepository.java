package com.mahsan.messenger.repository;


import com.mahsan.messenger.entity.Message;
import com.mahsan.messenger.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long> {
    Optional<Message> findById(Long id);

    Page<Message> findAllByFrom(Pageable pageable,User user);
    Page<Message> findAllByTo(Pageable pageable,User user);
    Page<Message> findAllByFrom_IdAndTo_Id(Pageable pageable,Long fromId,Long toId);
    Page<Message> findAllByFrom_PhoneAndTo_Phone(Pageable pageable,String fromPhone,String toPhone);


}
