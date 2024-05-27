package com.toursiteback.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CountryDto {
    private String ip;
    private String country;
}
