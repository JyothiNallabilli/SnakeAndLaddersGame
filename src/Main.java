import model.Snake;
import model.Ladder;
import model.Player;
import service.GameLogic;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Snake> snakes = Arrays.asList(new Snake(25, 5), new Snake(43, 15), new Snake(78, 39));
        List<Ladder> ladders = Arrays.asList(new Ladder(3, 22), new Ladder(8, 26), new Ladder(50, 90));
        List<Player> players = Arrays.asList(new Player("Alice",0), new Player("Bob",0));

        GameLogic game = new GameLogic(10, snakes, ladders, players);
        game.play();
    }
}
