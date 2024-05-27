package com.toursiteback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "states")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "time")
    private String time;
    @Column(name = "clicks")
    private Integer clicks;
    @Column(name = "messages")
    private Integer messages;
    @Column(name = "feedbacks")
    private Integer feedbacks;
}
