package org.tours.toursiteback.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tour_cards")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class TourCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}