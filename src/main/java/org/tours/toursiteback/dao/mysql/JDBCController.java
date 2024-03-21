package org.tours.toursiteback.dao.mysql;

import org.tours.toursiteback.request.AddNewTourRequest;
import org.tours.toursiteback.response.GetTourInfoResponse;
import org.tours.toursiteback.response.GetToursResponse;

import java.util.List;

public interface JDBCController {
    List<GetToursResponse> getTours();

    GetTourInfoResponse getTourInfo(int tourId);

    void addNewTour(AddNewTourRequest tour);
}