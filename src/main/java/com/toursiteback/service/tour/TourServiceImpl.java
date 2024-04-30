package com.toursiteback.service.tour;

import com.toursiteback.dto.TourCollectionDto;
import com.toursiteback.dto.TourDto;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
        List<TourDto> tours = modelConverter.convert(tourRepository.findAllBy());

        tours.forEach(tour -> {
            try {
                byte[] imageData = getImageData(tour.getImgUrl());
                tour.setImageData(imageData);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return TourCollectionDto.builder()
                .tours(tours)
                .build();
    }

    private byte[] getImageData(String imgUrl) throws IOException {
        Path imagePath = Path.of(imgUrl);
        return Files.readAllBytes(imagePath);
    }
}
