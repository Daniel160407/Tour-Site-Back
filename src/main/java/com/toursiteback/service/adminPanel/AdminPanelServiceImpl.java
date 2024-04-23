package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminPanelServiceImpl implements AdminPanelService {
    private final ModelConverter modelConverter;
    private final TourRepository tourRepository;
    private final AdminProvider adminProvider;

    @Autowired
    public AdminPanelServiceImpl(ModelConverter modelConverter, TourRepository tourRepository, AdminProvider adminProvider) {
        this.modelConverter = modelConverter;
        this.tourRepository = tourRepository;
        this.adminProvider = adminProvider;
    }

    @Override
    public void login(Admin admin) {
        if (!adminProvider.getAdminEmail().equals(admin.getEmail()) || !adminProvider.getAdminPassword().equals(admin.getPassword())) {
            throw new InvalidEmailOrPasswordException();
        }
    }

    @Override
    public TourDto addTour(TourDto tour) {
        tourRepository.save(modelConverter.convert(tour));
        return tour;
    }
}
