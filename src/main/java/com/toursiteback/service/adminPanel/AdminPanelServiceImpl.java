package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPanelServiceImpl implements AdminPanelService {
    private final ModelConverter modelConverter;
    private final TourRepository tourRepository;

    @Autowired
    public AdminPanelServiceImpl(ModelConverter modelConverter, TourRepository tourRepository) {
        this.modelConverter = modelConverter;
        this.tourRepository = tourRepository;
    }

    @Override
    public TourDto addTour(TourDto tour) {
        tourRepository.save(modelConverter.convert(tour));
        return tour;
    }
}
