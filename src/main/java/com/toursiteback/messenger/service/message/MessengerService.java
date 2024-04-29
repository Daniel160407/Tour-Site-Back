package com.toursiteback.messenger.service.message;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.model.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessengerService {
    MessageDto saveMessage(Message message);
}
