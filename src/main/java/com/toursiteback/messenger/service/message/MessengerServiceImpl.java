package com.toursiteback.messenger.service.message;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.repository.MessageRepository;
import com.toursiteback.util.ModelConverter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessengerServiceImpl implements MessengerService {
    private final MessageRepository messageRepository;
    private final ModelConverter modelConverter;

    @Autowired
    public MessengerServiceImpl(MessageRepository messageRepository, ModelConverter modelConverter) {
        this.messageRepository = messageRepository;
        this.modelConverter = modelConverter;
    }

    @Override
    public List<MessageDto> getMessages(String email) {
        return modelConverter.convertMessagesToDtoList(messageRepository.findAllBySenderEmailOrReceiverEmail(email, email));
    }

    @Override
    @Transactional
    public void deleteMessages(String email) {
        messageRepository.deleteAllBySenderEmailOrReceiverEmail(email, email);
    }
}
