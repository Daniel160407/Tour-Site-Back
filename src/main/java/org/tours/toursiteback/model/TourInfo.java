package org.tours.toursiteback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tour_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TourInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "direction")
    private String direction;

    @Column(name = "duration")
    private String duration;

    public TourInfo(String direction, String duration) {
        this.direction = direction;
        this.duration = duration;
    }
}
