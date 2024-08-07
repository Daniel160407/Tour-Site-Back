package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.messenger.model.User;
import com.toursiteback.messenger.repository.UsersRepository;
import com.toursiteback.model.Admin;
import com.toursiteback.model.Tour;
import com.toursiteback.model.TourWithImg;
import com.toursiteback.repository.TourRepository;
import com.toursiteback.service.exception.InvalidEmailOrPasswordException;
import com.toursiteback.util.ModelConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AdminPanelServiceImpl implements AdminPanelService {
    private final ModelConverter modelConverter;
    private final TourRepository tourRepository;
    private final UsersRepository usersRepository;
    private final AdminProvider adminProvider;

    @Autowired
    public AdminPanelServiceImpl(ModelConverter modelConverter, TourRepository tourRepository, UsersRepository usersRepository, AdminProvider adminProvider) {
        this.modelConverter = modelConverter;
        this.tourRepository = tourRepository;
        this.usersRepository = usersRepository;
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

    @Override
    public TourDto updateTour(TourDto tour) {
        Tour foundTour = tourRepository.findByName(tour.getName());
        foundTour.setName(tour.getName());
        foundTour.setDescription(tour.getDescription());
        foundTour.setDirection(tour.getDirection());
        foundTour.setDuration(tour.getDuration());
        foundTour.setHistory(tour.getHistory());
        foundTour.setRequirements(tour.getRequirements());
        foundTour.setPrice(tour.getPrice());
        foundTour.setImgUrl(tour.getImgUrl());
        tourRepository.save(foundTour);
        return tour;
    }

    @Override
    public TourDto deleteTour(String name) {
        Tour tourToDelete = tourRepository.findByName(name);
        List<Tour> foundTours = tourRepository.findAllByImgUrl(tourToDelete.getImgUrl());

        if (foundTours.size() == 1) {
            File file = new File(tourToDelete.getImgUrl());
            if (file.exists()) {
                file.delete();
            }
        }

        tourRepository.delete(tourToDelete);
        return modelConverter.convert(tourToDelete);
    }

    @Override
    public User getUser(String name) {
        return usersRepository.getUserByName(name);
    }

    @Override
    public void addImages(List<MultipartFile> images) {
        String uploadDir = "src/main/resources/images/";

        for (MultipartFile image : images) {
            try {
                Files.createDirectories(Paths.get(uploadDir));
                Path path = Paths.get(uploadDir + image.getOriginalFilename());
                Files.write(path, image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
