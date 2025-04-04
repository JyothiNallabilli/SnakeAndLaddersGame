package service;

import model.Snake;
import model.Ladder;
import model.Player;

import java.util.*;

public class GameLogic {
    private int boardSize;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Queue<Player> players;
    private Map<Integer, Integer> snakeMap;
    private Map<Integer, Integer> ladderMap;
    private Random dice;

    public GameLogic(int boardSize, List<Snake> snakes, List<Ladder> ladders, List<Player> players){
        this.boardSize= boardSize;
        this.snakes= snakes;
        this.ladders=ladders;
        this.players= new LinkedList<>(players);
        this.snakeMap=new HashMap<>();
        this.ladderMap = new HashMap<>();
        this.dice=new Random();

        for(Snake snake: snakes){
            snakeMap.put(snake.getHead(), snake.getTail());
        }
        for(Ladder ladder: ladders){
            ladderMap.put(ladder.getStart(), ladder.getEnd());
        }
    }
    private int rollDice(){
        return dice.nextInt(6)+1;
    }
    public void play(){
        while(true){
            Player player=players.poll();
            int diceValue=rollDice();
            int newPosition=player.getPosition()+diceValue;
            System.out.println(player.getName() + " rolled a "+ diceValue);

            if (newPosition>boardSize*boardSize){
                players.offer(player);
                continue;
            }
            if(snakeMap.containsKey(newPosition)){
                System.out.println("Oops! Bitten By a Snake. Moving down to " + snakeMap.get(newPosition));
                newPosition=snakeMap.get(newPosition);
            }else if(ladderMap.containsKey(newPosition)){
                System.out.println("Yay! Climbed a Ladder. Moving up to " +ladderMap.get(newPosition));
                newPosition=ladderMap.get(newPosition);
            }
            player.setPosition(newPosition);
            System.out.println(player.getName() + " is now at position " + newPosition);

            if(newPosition==boardSize*boardSize){
                System.out.println(player.getName()+ " wins!");
                break;
            }
            players.offer(player);
        }
    }
    
}
