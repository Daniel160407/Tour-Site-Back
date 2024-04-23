package com.toursiteback.util;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Tour;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelConverter {
    public TourCollectionDto convertTours(List<Tour> tours) {
        List<TourDto> tourDtos = tours.stream()
                .map(this::convertTourToDto)
                .toList();

        return TourCollectionDto.builder()
                .tours(tourDtos)
                .build();
    }

    private TourDto convertTourToDto(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .build();
    }

    public List<TourDto> convert(List<Tour> tours) {
        List<TourDto> tourDtos = new ArrayList<>();

        for (Tour tour : tours) {
            tourDtos.add(convertTourToDto(tour));
        }
        return tourDtos;
    }

    public Tour convert(TourDto tourDto) {
        return Tour.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .build();
    }
}
