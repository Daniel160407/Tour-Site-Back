package com.toursiteback.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tour")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "direction")
    private String direction;
    @Column(name = "history")
    private String history;
    @Column(name = "requirements")
    private String requirements;
    @Column(name = "price")
    private String price;
    @Column(name = "language")
    private String language;
    @Column(name = "img_url")
    private String imgUrl;
}
