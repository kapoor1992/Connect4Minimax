package connect4.game.gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

// The GUI.
public class Display extends JFrame {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    
    private JPanel grid;
    private int width;
    private int height;
    private char aiPiece;
    private char playerPiece;
    
    public Display (int width, int height, char aiPiece, char playerPiece) {
        this.width = width;
        this.height = height;
        this.aiPiece = aiPiece;
        this.playerPiece = playerPiece;
        
        initDisplay();
    }
    
    // Setup window.
    public void initDisplay() {
        setResizable(false);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        grid = new JPanel();
        grid.setLayout(new GridLayout(ROWS, COLS, 1, 1));
        grid.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    }
    
    // Display window based on board configuration.
    public void setGrid (char[][] board) {
        char[] mapped = map(board); // Transform board into an easily traversable format.
        
        // Draw the board based on characters in input.
        for (int i = 0; i < ROWS * COLS; i++) {
            if (mapped == null) {
                grid.add(new Spot(Color.GRAY));
            } else {
                if (mapped[i] == aiPiece)
                    grid.add(new Spot(Color.BLACK));
                else if (mapped[i] == playerPiece)
                    grid.add(new Spot(Color.WHITE));
                else
                    grid.add(new Spot(Color.GRAY));
            }
        }
        
        add(grid);
        setVisible(true);
    }
    
    // Map 2D char array to 1D char array.
    private char[] map (char[][] board) {
        if (board == null)
            return null;
        
        char[] result = new char[ROWS * COLS];
        int index = 0;
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                result[index] = board[i][j];
                index++;
            }
        }
        
        return result;
    }
}