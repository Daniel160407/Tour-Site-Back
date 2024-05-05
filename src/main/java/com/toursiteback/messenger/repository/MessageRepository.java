package com.toursiteback.messenger.repository;

import com.toursiteback.messenger.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    Message save(Message message);

    List<Message> findAllBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);

    void deleteAllBySenderEmailOrReceiverEmail(String senderEmail, String receiverEmail);
}
