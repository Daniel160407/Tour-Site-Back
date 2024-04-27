package com.toursiteback.util;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Tour;
import com.toursiteback.model.TourWithImg;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ModelConverter {
    public TourCollectionDto convertTours(List<Tour> tours) {
        List<TourDto> tourDtos = tours.stream()
                .map(this::convert)
                .toList();

        return TourCollectionDto.builder()
                .tours(tourDtos)
                .build();
    }

    public TourDto convert(Tour tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .imgUrl(tour.getImgUrl())
                .build();
    }

    public List<TourDto> convert(List<Tour> tours) {
        List<TourDto> tourDtos = new ArrayList<>();

        for (Tour tour : tours) {
            tourDtos.add(convert(tour));
        }
        return tourDtos;
    }

    public Tour convert(TourDto tourDto) {
        return Tour.builder()
                .name(tourDto.getName())
                .description(tourDto.getDescription())
                .direction(tourDto.getDirection())
                .history(tourDto.getHistory())
                .price(tourDto.getPrice())
                .imgUrl(tourDto.getImgUrl())
                .build();
    }

    public Tour convert(TourWithImg tour, String imgUrl) {
        return Tour.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .imgUrl(imgUrl)
                .build();
    }

    public TourDto convert(TourWithImg tour) {
        return TourDto.builder()
                .name(tour.getName())
                .description(tour.getDescription())
                .direction(tour.getDirection())
                .history(tour.getHistory())
                .price(tour.getPrice())
                .build();
    }
}
