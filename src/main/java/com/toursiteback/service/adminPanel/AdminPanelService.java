package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import com.toursiteback.model.Admin;
import org.springframework.stereotype.Service;

@Service
public interface AdminPanelService {
    void login(Admin admin);
    TourDto addTour(TourDto tour);
}
