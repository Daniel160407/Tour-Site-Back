package org.tours.toursiteback.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import org.tours.toursiteback.model.TourCard;
import org.tours.toursiteback.response.GetToursResponse;

import java.util.ArrayList;
import java.util.List;

public class MySQLController implements JDBCController {
    private final JDBCConnector jdbcConnector = JDBCConnector.getInstance();

    @Override
    public List<GetToursResponse> getTours() {
        jdbcConnector.initializeTourCardCriteria();

        CriteriaQuery<TourCard> select = jdbcConnector.getTourCardCriteriaQuery().select(
                jdbcConnector.getTourCardRoot()
        );

        TypedQuery<TourCard> typedQuery = jdbcConnector.getEntityManager().createQuery(select);

        List<TourCard> tourCards = typedQuery.getResultList();
        List<GetToursResponse> toursResponses = new ArrayList<>();
        tourCards.forEach(tourCard -> toursResponses.add(new GetToursResponse(tourCard.getId(), tourCard.getTitle(), tourCard.getDescription())));

        return toursResponses;
    }
}