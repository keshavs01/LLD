package org.example;

import org.example.service.GameService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        GameService gameService = new GameService();
        gameService.startGame();
    }
}