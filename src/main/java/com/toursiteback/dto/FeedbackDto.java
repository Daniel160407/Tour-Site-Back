package com.toursiteback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FeedbackDto {
    private String name;
    private String time;
    private String comment;
}
