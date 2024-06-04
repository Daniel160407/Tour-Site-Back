package com.toursiteback.messenger.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toursiteback.messenger.dto.MessageDto;
import com.toursiteback.messenger.model.Message;
import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.MessageRepository;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.service.states.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class MessengerEndpoint extends TextWebSocketHandler {
    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final UsersRepository usersRepository;
    private final MessageRepository messageRepository;
    private final StatisticsService statisticsService;

    @Autowired
    public MessengerEndpoint(UsersRepository usersRepository, MessageRepository messageRepository, StatisticsService statisticsService) {
        this.usersRepository = usersRepository;
        this.messageRepository = messageRepository;
        this.statisticsService = statisticsService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);

        sessions.add(session);
        session.sendMessage(new TextMessage(objectMapper.writeValueAsString(new MessageDto("server", session.getId()))));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
        super.handleTextMessage(session, textMessage);
        String payload = textMessage.getPayload();

        Message message = objectMapper.readValue(payload, Message.class);

        if (usersRepository.getUserByEmail(message.getSenderEmail()) != null) {
            if (message.getReceiver().equals("Admin")) {
                User admin = usersRepository.getUserByName("Admin");
                for (WebSocketSession receiverSession : sessions) {
                    if (receiverSession.getId().equals(admin.getSid())) {
                        receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                    }
                }
                statisticsService.increaseMessages();
                usersRepository.updatePositionForSpecificEmail(message.getSenderEmail());
                usersRepository.incrementPositionForAllExceptSpecificEmail(message.getSenderEmail());
            } else {
                User client = usersRepository.getUserByEmail(message.getReceiverEmail());
                for (WebSocketSession receiverSession : sessions) {
                    if (receiverSession.getId().equals(client.getSid())) {
                        receiverSession.sendMessage(new TextMessage(objectMapper.writeValueAsString(message)));
                    }
                }
                usersRepository.updatePositionForSpecificEmail(message.getReceiverEmail());
                usersRepository.incrementPositionForAllExceptSpecificEmail(message.getReceiverEmail());
            }
            this.messageRepository.save(message);
        }
    }
}