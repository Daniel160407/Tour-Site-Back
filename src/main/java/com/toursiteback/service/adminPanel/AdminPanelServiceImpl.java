package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.model.TourWithImg;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public TourDto addTour(TourWithImg tour) {
        String uploadDir = "src/main/resources/images/";

        try {
            Files.createDirectories(Paths.get(uploadDir));
            Path path = Paths.get(uploadDir + tour.getImage().getOriginalFilename());
            Files.write(path, tour.getImage().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        tourRepository.save(modelConverter.convert(tour, uploadDir + tour.getImage().getOriginalFilename()));
        return modelConverter.convert(tour);
    }
}
