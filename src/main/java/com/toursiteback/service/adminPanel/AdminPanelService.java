package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.messenger.model.User;
import com.toursiteback.model.Admin;
import com.toursiteback.model.TourWithImg;
import org.springframework.stereotype.Service;

@Service
public interface AdminPanelService {
    void login(Admin admin);

    TourDto addTour(TourWithImg tour);

    TourDto updateTour(TourDto tour);

    TourDto deleteTour(String name);
    User getUser(String name);
}
