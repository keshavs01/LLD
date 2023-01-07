package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Getter
public class Board {

    private static final Logger LOGGER = Logger.getLogger( Board.class.getName() );

    @Setter
    public Boolean isGameFinished;
    private final Integer boardSize;

    Map<Integer, Snake> snakeMap = new HashMap<>();
    Map<Integer, Ladder> ladderMap = new HashMap<>();
    List<Move> movesList = new LinkedList<>();
    Queue<Player> playerQueue = new LinkedList<>();

    public Board(Integer boardSize, Map<Integer, Snake> snakeMap,  Map<Integer, Ladder> ladderMap, Queue<Player> playerQueue){
        this.boardSize = boardSize;
        this.snakeMap = snakeMap;
        this.ladderMap = ladderMap;
        this.playerQueue = playerQueue;
        isGameFinished = false;
    }

//    private void initializeGame(){
//        for(int i=1;i<=boardSize;i++){
//            boardMap.put(i,new Slot(i, null));
//        }
//    }

    public Player getNextPlayer(){
        Player player  = playerQueue.poll();
        playerQueue.add(player);
        return player;
    }

    public void logMoveHistory(Move move){
        movesList.add(move);
        printLog(move);
    }

    public void printLog(Move move) {
        System.out.println("Dice: " + move.diceValue + "  " + move.player.getName() +
                " moved from " + move.initialSlot + " to " + move.finalSlot +
                " Snake : " + move.snake + " Ladder : " + move.ladder);
        if(move.finalSlot==boardSize)
            System.out.println(move.player.getName() + " has WON the game ");

    }

}
