package com.toursiteback.service.adminPanel;

import com.toursiteback.dto.TourDto;
import org.springframework.stereotype.Service;

@Service
public interface AdminPanelService {
    TourDto addTour(TourDto tour);
}
