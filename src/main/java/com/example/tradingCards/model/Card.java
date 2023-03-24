package com.example.tradingCards.model;


import jakarta.persistence.*;
import lombok.*;

import java.lang.management.PlatformLoggingMXBean;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String type;
    private String position;
    /*private String skills;
    private String weakFoot;
    private String foot;
    private Integer pace;
    private Integer dribbling;
    private Integer shoot;
    private Integer defending;
    private Integer passing;
    private Integer physical;*/
    private Double minPrice;
    private Double maxPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pack> packList;

    @OneToOne(cascade = CascadeType.MERGE)
    private Player player;

    @OneToOne(cascade = CascadeType.ALL)
    private Market market;

}