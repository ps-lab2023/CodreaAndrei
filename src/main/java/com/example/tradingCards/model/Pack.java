package com.example.tradingCards.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private Integer size;
    private Double price;

    @ManyToMany
    @JoinTable(
        name = "pack_content",
        joinColumns = @JoinColumn(name = "id_pack"),
        inverseJoinColumns = @JoinColumn(name = "id_card"))
    List<Card> cardList;
}
