package org.example.service;

import org.example.entity.*;
import org.example.exceptions.MoveNotPossibleException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class GameService {

    public void startGame(){
        HashMap<Integer, Snake> snakeMap = new HashMap<>();
        HashMap<Integer, Ladder> ladderMap = new HashMap<>();
        snakeMap.put(80, new Snake(80,12));
        snakeMap.put(23, new Snake(23,2));
        snakeMap.put(43, new Snake(43,31));
        snakeMap.put(94, new Snake(94,72));
        snakeMap.put(77, new Snake(77,51));

        ladderMap.put(6, new Ladder(6,18));
        ladderMap.put(41, new Ladder(41,56));

        Queue<Player> playerQueue = new LinkedList<>();
        playerQueue.add(new Player("1","Keshav","email@keshav.com","Red"));
        playerQueue.add(new Player("2","Khaaj","chirag@khaaj.com","Green"));

        Board board = new Board(100, snakeMap, ladderMap,playerQueue);

        while(!board.isGameFinished){
            try{
                movePlayer(board);
            } catch (MoveNotPossibleException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void movePlayer(Board board) throws MoveNotPossibleException {
        Player player = board.getNextPlayer();
        Integer diceValue = Dice.rollDice();
        int oldValue = player.getPosition();
        int newValue = oldValue + diceValue;
        newValue = newPositionAfterSnakeAndLadder(board, newValue);
        validDateMove(player, diceValue, newValue, board);
        player.setPosition(newValue);
        Move move = new Move(diceValue, oldValue, newValue, player, board.getSnakeMap().get(oldValue + diceValue), board.getLadderMap().get(oldValue + diceValue));
        board.logMoveHistory(move);
    }

    public int newPositionAfterSnakeAndLadder(Board board, int position){
        Snake snake = board.getSnakeMap().get(position);
        if(null!=snake)
            return snake.getToSlot();
        Ladder ladder = board.getLadderMap().get(position);
        if(null!=ladder)
            return ladder.getToSlot();
        return position;
    }

    public void validDateMove(Player player, int diceValue, int value, Board board) throws MoveNotPossibleException {
        if(value==board.getBoardSize()) {
            board.setIsGameFinished(true);
            return;
        }
        if (value > board.getBoardSize()) {
            throw new MoveNotPossibleException("Dice : " + diceValue + " " + player.getName() + " Move not possible from " + player.getPosition());
        }
    }
}
