package com.toursiteback.messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageDto {
    private String senderEmail;
    private String receiverEmail;
    private String sender;
    private String receiver;
    private String message;

    public MessageDto(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }
}
