package com.toursiteback.messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class MessageDto {
    private String sender;
    private String receiver;
    private String message;
}
