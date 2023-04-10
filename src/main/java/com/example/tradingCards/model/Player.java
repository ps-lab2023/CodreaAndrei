package com.example.tradingCards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private Integer age;
    private String nationality;
    private String league;
    private String team;

    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "player")
    private List<Card> cardList;


}
