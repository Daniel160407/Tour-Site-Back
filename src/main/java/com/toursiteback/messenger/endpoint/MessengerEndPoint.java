package com.toursiteback.messenger.endpoint;

import com.toursiteback.messenger.message.Message;
import com.toursiteback.messenger.util.MessageDecoder;
import com.toursiteback.messenger.util.MessageEncoder;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(
        value = "/messenger",
        decoders = {MessageDecoder.class},
        encoders = {MessageEncoder.class}
)
public class MessengerEndPoint {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    private void sendMessage(Session session, Message message) {
        try {
            session.getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(Message message, Session session) {
    }
}
