package connect4.game;

import java.util.Scanner;
import connect4.game.negamax.Negamax;
import connect4.game.board.Board;
import connect4.game.gui.Display;

// High-level coordinator.
public class GameController {
    public static final char PLAYER_PIECE = 'X';
    public static final char AI_PIECE = 'O';
    
    private Scanner scanner;
    private Board board;
    private Negamax negamax;
    private Display display;
    
    public GameController (int width, int height) {
        scanner = new Scanner(System.in);
        board = new Board();
        negamax = new Negamax(AI_PIECE, PLAYER_PIECE);
        display = new Display(width, height, AI_PIECE, PLAYER_PIECE);
    }
    
    // High-level execution.
    public void run() {
        int col;
        boolean playerTurn = true;
        boolean didWin;
        
        // Display an empty game.
        display.setGrid(null);
        
        while (true) {
            // Get out if board is full.
            if (board.allFull()) {
                System.out.println("Board is full. It's a tie!");
                break;
            }
            
            // Execute player turn.
            if (playerTurn) {
                col = getCol();
                board.dropPiece(col, PLAYER_PIECE);
                didWin = board.didWin(col, PLAYER_PIECE);
            // Execute computer turn.
            } else {
                col = negamax.search(board); // Search algorithm is called.
                System.out.println("The computer went in column " + col);
                board.dropPiece(col, AI_PIECE);
                didWin = board.didWin(col, AI_PIECE);
            }
            
            System.out.println();
            
            // Display changes.
            display.setGrid(board.get());
            
            // Check winning conditions and break if necessary.
            if (didWin) {
                if (playerTurn)
                    System.out.println("You win!");
                else
                    System.out.println("You lose!");
                
                break;
            }
            
            // Small delay allows proper display to be presented if negamax takes too long.
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
            }
            
            // Get ready for next iteration.
            playerTurn = !playerTurn;
            display.initDisplay();
        }
        
        // Display final state.
        display.initDisplay();
        display.setGrid(board.get());
    }
    
    // Gets user input for a column.
    private int getCol() {
        String response;
        int col;
        
        try {
            System.out.print("Enter in a column: ");
            response = scanner.next();
            col = Integer.parseInt(response);
            
            if (col >= 0 && col < Board.COLS && !board.colFull(col))
                return col;
            else
                throw new IllegalArgumentException(); // Force a fail for bad input.
            
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.\n");
            return getCol();
        }
    }
}
