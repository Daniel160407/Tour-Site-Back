package com.toursiteback.messenger.service.message;

import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.model.Message;
import com.toursiteback.messenger.repository.MessageRepository;
import com.toursiteback.util.ModelConverter;
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
    public MessageDto saveMessage(Message message) {
        System.out.println(message);
        return ModelConverter.convert(messageRepository.save(message));
    }

    @Override
    public List<MessageDto> getMessages(String email) {
        return modelConverter.convertMessagesToDtoList(messageRepository.findAllBySenderEmail(email));
    }
}
