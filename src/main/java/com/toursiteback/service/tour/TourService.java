package com.toursiteback.service.tour;

import com.toursiteback.dto.TourCollectionDto;
import org.springframework.stereotype.Service;

@Service
public interface TourService {
    TourCollectionDto getTours(String language);
}
