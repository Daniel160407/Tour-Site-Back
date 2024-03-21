package org.tours.toursiteback.dao.mysql;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaQuery;
import org.tours.toursiteback.dao.mysql.JDBCConnector;
import org.tours.toursiteback.dao.mysql.JDBCController;
import org.tours.toursiteback.model.TourCard;
import org.tours.toursiteback.model.TourInfo;
import org.tours.toursiteback.request.AddNewTourRequest;
import org.tours.toursiteback.response.GetTourInfoResponse;
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

    @Override
    public GetTourInfoResponse getTourInfo(int tourId) {
        jdbcConnector.initializeTourInfoCriteria();

        CriteriaQuery<TourInfo> select = jdbcConnector.getTourInfoCriteriaQuery().select(
                jdbcConnector.getTourInfoRoot()
        ).where(jdbcConnector.getCriteriaBuilder().equal(jdbcConnector.getTourInfoRoot().get("id"), tourId));

        TypedQuery<TourInfo> typedQuery = jdbcConnector.getEntityManager().createQuery(select);

        TourInfo tourInfo = typedQuery.getSingleResult();

        return new GetTourInfoResponse(tourInfo.getId(), tourInfo.getDirection(), tourInfo.getDuration());
    }

    @Override
    public void addNewTour(AddNewTourRequest tour) {
        jdbcConnector.initializeTourInfoCriteria();

        try {
            jdbcConnector.getEntityTransaction().begin();

            TourInfo tourInfo = new TourInfo(tour.direction(), tour.duration());
            jdbcConnector.getEntityManager().persist(tourInfo); //TODO: It may has bugs

            jdbcConnector.getEntityTransaction().commit();


            jdbcConnector.initializeTourCardCriteria();
            jdbcConnector.getEntityTransaction().begin();

            TourCard tourCard = new TourCard(2,tour.title(), tour.description());
            jdbcConnector.getEntityManager().persist(tourCard);  //TODO: It may has bugs

            jdbcConnector.getEntityTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            if (jdbcConnector.getEntityTransaction().isActive()) {
                jdbcConnector.getEntityTransaction().rollback();
            }
        }
    }
}