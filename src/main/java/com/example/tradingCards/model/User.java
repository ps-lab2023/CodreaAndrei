package com.example.tradingCards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity



public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String name;
    private String password;

    public enum Type {
        REGULAR,
        ADMIN,
    }

    private Type role;


    @ManyToMany
    @JoinTable(
            name = "card_owner",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_card"))
    List<Card> ownedCards = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    List<Market> listing;

    public void addToOwnedCards(Card card) {
        ownedCards.add(card);
    }

}
