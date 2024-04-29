package com.toursiteback.messenger.service.message;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.model.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerService {
    MessageDto saveMessage(Message message);
    List<MessageDto>getMessages(String email);
}
