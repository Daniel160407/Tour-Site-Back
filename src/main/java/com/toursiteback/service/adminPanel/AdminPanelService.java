package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import com.toursiteback.model.TourWithImg;
import org.springframework.stereotype.Service;

@Service
public interface AdminPanelService {
    void login(Admin admin);
    TourDto addTour(TourWithImg tour);
}
