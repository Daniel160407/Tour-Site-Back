package com.toursiteback.service.tour;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourServiceImpl implements TourService {
    private final ModelConverter modelConverter;
    private final TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(ModelConverter modelConverter, TourRepository tourRepository) {
        this.modelConverter = modelConverter;
        this.tourRepository = tourRepository;
    }

    @Override
    public TourCollectionDto getTours() {
        return TourCollectionDto.builder()
                .tours(modelConverter.convert(tourRepository.findAllBy()))
                .build();
    }
}
