package com.toursiteback.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourDto {
    private String name;
    private String description;
}
