package com.toursiteback.messenger.service.message;

import com.toursiteback.messenger.dto.MessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerService {
    List<MessageDto>getMessages(String email);
    void deleteMessages(String email);
}
