package org.example.entity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Move {
    Integer diceValue;
    Integer initialSlot;
    Integer finalSlot;
    Player player;
    Snake snake;
    Ladder ladder;
}
