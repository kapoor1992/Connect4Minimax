package connect4.game.board;

// Used for back-end board representation.
public class Board {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final char NULL_CHAR = 'N';
    
    private char[][] board;
    
    // General constructor.
    public Board() {
        board = new char[ROWS][COLS];
        init();
    }
    
    // Copy constructor used for recursion.
    public Board (Board board) {
        this.board = board.boardCopy();
    }
    
    // Retrieves board.
    public char[][] get() {
        return board;
    }
    
    // Drops a piece in a column.
    public void dropPiece (int col, char piece) {
        if (col < 0 || col >= COLS)
            return;
            
        for (int i = ROWS - 1; i >= 0; i--) {
            if (board[i][col] == ' ') {
                board[i][col] = piece;
                    break;
            }
        }
    }
    
    // Gets a character at a given location.
    public char getCell (int row, int col) {
        if (row < 0 || row >= ROWS || col < 0 || col >= COLS)
            return NULL_CHAR;
        return board[row][col];
    }
    
    // Checks if a column is full.
    public boolean colFull (int col) {
        if (board[0][col] == ' ')
            return false;
        return true;
    }
    
    // Checks if the board is full.
    public boolean allFull() {
        for (int i = 0; i < COLS; i++) {
            if (board[0][i] == ' ')
                return false;
        }
        return true;
    }
    
    // Checks if a winning condition has been met.
    public boolean didWin (int col, char piece) {
        for (int row = 0; row < ROWS; row++) {
            if (verticalWin(row, col, piece) || horizontalWin(row, col, piece) || 
                leadingDiagonalWin(row, col, piece) || trailingDiagonalWin(row, col, piece))
                return true;
        }
        return false;
    }
    
    // Initializes board with space characters.
    private void init() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++)
                board[i][j] = ' ';
        }
    }
    
    // Copies the board and gets it.
    private char[][] boardCopy() {
        char[][] result = new char[ROWS][COLS];
        
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++)
                result[i][j] = board[i][j];
        }
        
        return result;
    }
    
    // Checks for a vertical win from top to bottom.
    private boolean verticalWin (int row, int col, char piece) {
        int count = 0;

        for (int i = row; i < ROWS; i++) {
            if (board[i][col] == piece) {
                count++;
            } else {
                break;
            }
        }

        for (int i = row - 1; i >= 0; i--) {
            if (board[i][col] == piece) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
    
    // Checks for a horizontal win from left to right.
    private boolean horizontalWin(int row, int col, char piece) {
        int count = 0;

        for (int i = col; i < COLS; i++) {
            if (board[row][i] == piece) {
                count++;
            } else {
                break;
            }
        }

        for (int i = col - 1; i >= 0; i--) {
            if (board[row][i] == piece) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
    
    // Checks for a diagonal win from top-left to bottom-right.
    private boolean leadingDiagonalWin(int row, int col, char piece) {
        int count = 0;

        for (int i = row, j = col; i < ROWS && j < COLS; i++, j++) {
            if (board[i][j] == piece) {
                count++;
            } else {
                break;
            }
        }

        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == piece) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
    
    // Checks for a diagonal win from top-right to bottom-left.
    private boolean trailingDiagonalWin(int row, int col, char piece) {
        int count = 0;

        for (int i = row, j = col; i >= 0 && j < COLS; i--, j++) {
            if (board[i][j] == piece) {
                count++;
            } else {
                break;
            }
        }

        for (int i = row + 1, j = col - 1; i < ROWS && i >= 0 && j >= 0; i++, j--) {
            if (board[i][j] == piece) {
                count++;
            } else {
                break;
            }
        }

        return count >= 4;
    }
}
