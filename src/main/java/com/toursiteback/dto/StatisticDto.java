package com.toursiteback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticDto {
    private String time;
    private Integer clicks;
    private Integer users;
    private Integer messages;
    private Integer feedbacks;
}
