package com.toursiteback.messenger.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toursiteback.messenger.model.Message;
import jakarta.websocket.Decoder;

public class MessageDecoder implements Decoder.Text<Message> {
    @Override
    public Message decode(String s) {
        Message message;

        try {
            message = new ObjectMapper().readValue(s, Message.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    @Override
    public boolean willDecode(String s) {
        if (s == null) {
            return false;
        }
        return decode(s) != null;
    }
}
