package com.eijproject.swarmandhive.services;

import com.eijproject.swarmandhive.entities.Card;
import com.eijproject.swarmandhive.entities.Stage;

import java.util.List;

public class Services {
    private AuthService authService;
    private CardService cardService;
    private StageService stageService;

    private static Services instance;

    public Services() {
        authService = new AuthService();
        cardService = new CardService();
        stageService = new StageService();
    }

    public static Services getInstance() {
        if(instance == null) instance = new Services();
        return instance;
    }

    public List<Card> getDeckCards() {
        return cardService.getDeckCards();
    }

    public List<Stage> getStages() {
        return stageService.getAll();
    }

    public void login(String email, String password) {
        authService.login(email, password);
    }

    public void register(String email, String username, String password) {
        authService.register(email, username, password);
    }
}
