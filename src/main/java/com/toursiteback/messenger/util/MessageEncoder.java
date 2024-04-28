package com.toursiteback.messenger.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toursiteback.messenger.message.Message;
import jakarta.websocket.Encoder;

public class MessageEncoder implements Encoder.Text<Message> {
    @Override
    public String encode(Message message) {
        try {
            return new ObjectMapper().writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
