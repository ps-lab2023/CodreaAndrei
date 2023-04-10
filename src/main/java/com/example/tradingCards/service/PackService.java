package com.example.tradingCards.service;

import org.springframework.stereotype.Component;

@Component
public interface PackService {

    void createPack(String name, String description, Integer size, Double price);
    void deletePack(String name);
}
