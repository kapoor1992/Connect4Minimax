package connect4;

import connect4.game.GameController;

// Entry point.
public class Connect4Main {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;
    
    public static void main(String[] args) {
        intro();
        
        GameController gc = new GameController(WIDTH, HEIGHT);
        gc.run();
    }
    
    public static void intro() {
        System.out.println("Welcome to Connect4!\n" +
                           "You play against the computer, which uses a minimax algorithm to make its moves.\n" +
                           "Columns must be specified from 0 (far left) to 6 (far right) when making moves.\n" +
                           "Good luck!\n\n");
    }
}
