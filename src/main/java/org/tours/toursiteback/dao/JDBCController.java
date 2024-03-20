package org.tours.toursiteback.dao;

import org.tours.toursiteback.response.GetTourInfoResponse;
import org.tours.toursiteback.response.GetToursResponse;

import java.util.List;

public interface JDBCController {
    List<GetToursResponse> getTours();

    GetTourInfoResponse getTourInfo(int tourId);
}