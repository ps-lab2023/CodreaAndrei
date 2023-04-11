package com.example.tradingCards.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CardDTO {

    private Long id;
    private String type;
    private String position;
    private  int overall;
    private int minPrice;
    private int maxPrice;
    private Double chance;

}
