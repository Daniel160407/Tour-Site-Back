package org.tours.toursiteback.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Table(name = "tour_cards")
public class TourCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    public TourCard(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public TourCard(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public TourCard() {
    }

}