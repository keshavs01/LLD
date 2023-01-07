package org.example.entity;


import java.util.Random;

public class Dice {
    public static Integer rollDice() {
        return (new Random().nextInt(6) % 6) + 1;
    }
}
