package org.tours.toursiteback.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.Getter;
import org.tours.toursiteback.model.TourCard;
import org.tours.toursiteback.model.TourInfo;

@Getter
public class JDBCConnector {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("tours");
    private final EntityManager entityManager = entityManagerFactory.createEntityManager();
    private final EntityTransaction entityTransaction = entityManager.getTransaction();

    private final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

    //TourCard
    private CriteriaQuery<TourCard> tourCardCriteriaQuery = criteriaBuilder.createQuery(TourCard.class);
    private Root<TourCard> tourCardRoot = tourCardCriteriaQuery.from(TourCard.class);

    //TourInfo
    private CriteriaQuery<TourInfo> tourInfoCriteriaQuery = criteriaBuilder.createQuery(TourInfo.class);
    private Root<TourInfo> tourInfoRoot = tourInfoCriteriaQuery.from(TourInfo.class);

    //other

    public static JDBCConnector instance;

    public static JDBCConnector getInstance() {
        if (instance == null) {
            instance = new JDBCConnector();
        }
        return instance;
    }

    public void initializeTourCardCriteria() {
        tourCardCriteriaQuery = criteriaBuilder.createQuery(TourCard.class);
        tourCardRoot = tourCardCriteriaQuery.from(TourCard.class);
    }

    public void initializeTourInfoCriteria() {
        tourInfoCriteriaQuery = criteriaBuilder.createQuery(TourInfo.class);
        tourInfoRoot = tourInfoCriteriaQuery.from(TourInfo.class);
    }
}