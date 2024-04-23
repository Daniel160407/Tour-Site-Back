package com.toursiteback.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TourCollectionDto {
    private List<TourDto> tours;
}
