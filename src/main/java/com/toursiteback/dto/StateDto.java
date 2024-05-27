package com.toursiteback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StateDto {
    private String time;
    private Integer clicks;
    private Integer messages;
    private Integer feedbacks;
}
