package com.toursiteback.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class TourWithImg {
    private String name;
    private String description;
    private String direction;
    private String duration;
    private String history;
    private String requirements;
    private String price;
    private String language;
    private MultipartFile image;
}
