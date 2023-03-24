package com.example.tradingCards.service.impl;

import com.example.tradingCards.repository.MarketRepository;
import com.example.tradingCards.service.MarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketServiceImpl implements MarketService {

    @Autowired
    private MarketRepository marketRepository;

    public MarketServiceImpl(MarketRepository marketRepository) { this.marketRepository = marketRepository; }

}
