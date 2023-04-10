package com.example.tradingCards.model;


import jakarta.persistence.*;
import lombok.*;

import java.lang.management.PlatformLoggingMXBean;
import java.util.ArrayList;
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
    /*
    private Integer pace;
    private Integer dribbling;
    private Integer shoot;
    private Integer defending;
    private Integer passing;
    private Integer physical;*/
    private  Double overall;
    private Double minPrice;
    private Double maxPrice;
    private Double chance;

    @ManyToMany(mappedBy = "ownedCards")
    private List<User> owners;

    @ManyToMany (mappedBy = "cardList")
    private List<Pack> packList;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Player player;

    @OneToMany(cascade = CascadeType.ALL)
    List<Market> listing;

    public void addToPackList(Pack pack){
        packList.add(pack);
    }

    public void addToOwners(User user){
        owners.add(user);
    }

}