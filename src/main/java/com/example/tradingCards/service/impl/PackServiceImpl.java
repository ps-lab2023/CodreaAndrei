package com.example.tradingCards.service.impl;

import com.example.tradingCards.model.Pack;
import com.example.tradingCards.repository.PackRepository;
import com.example.tradingCards.service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    public PackServiceImpl (PackRepository packRepository) { this.packRepository = packRepository; }


    @Override
    public void createPack(String name, String description, Integer size, Double chance, Double price) {

        Pack newPack = new Pack();
        newPack.setName(name);
        newPack.setDescription(description);
        newPack.setSize(size);
        newPack.setChance(chance);
        newPack.setPrice(price);
        packRepository.save(newPack);
    }

    @Override
    public void deletePack(String name) {
        packRepository.delete(packRepository.findFirstByName(name));
    }
}
