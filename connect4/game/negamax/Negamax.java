package connect4.game.negamax;

import connect4.game.board.Board;

// Minimax implementation.
public class Negamax {
    public static final int MAX_DEPTH = 5;   // Searches 5 levels deep.
    
    private Node root;
    private char aiPiece;
    private char playerPiece;
    
    public Negamax (char aiPiece, char playerPiece) {
        root = null;
        this.aiPiece = aiPiece;
        this.playerPiece = playerPiece;
    }
    
    // Get the best column choice based on the provided board.
    public int search (Board board) {
        root = new Node();
        
        // Get best score among children.
        int best = negamax(board, root, MAX_DEPTH, false);
        
        // Get the column of the best child.
        int col = getCol(best);
        
        // As long as a valid column is given, return.
        if (col != -1)
            return col;
        
        // Flip score sign if we ran into sign problems.
        col = getCol(-best);
        
        // As long as a valid column is given, return.
        if (col != -1)
            return col;
        
        // Fail-safe (never actually returned).
        return 3;
    }
    
    // Recursive function for negamax.
    private int negamax (Board board, Node node, int depth, boolean aiTurn) {
        // If we went as deep as we could, evaluate the node.
        if (depth == 0) {
            return node.score = calcScore(board);
        }
        
        // Best score and children scores initialized to "practical negative infinity".
        int best = Integer.MIN_VALUE;
        int v0 = Integer.MIN_VALUE;
        int v1 = Integer.MIN_VALUE;
        int v2 = Integer.MIN_VALUE;
        int v3 = Integer.MIN_VALUE;
        int v4 = Integer.MIN_VALUE;
        int v5 = Integer.MIN_VALUE;
        int v6 = Integer.MIN_VALUE;
        
        // Recurse through all relevant children.
        if (!board.colFull(0)) {
            Board b0 = new Board(board);
            if (aiTurn)
                b0.dropPiece(0, aiPiece);
            else
                b0.dropPiece(0, playerPiece);
            Node c0 = new Node(v0);
            node.c0 = c0;
            v0 = -negamax(b0, c0, depth - 1, !aiTurn);
            c0.score = v0;
        }
        if (!board.colFull(1)) {
            Board b1 = new Board(board);
            if (aiTurn)
                b1.dropPiece(1, aiPiece);
            else
                b1.dropPiece(1, playerPiece);
            Node c1 = new Node(v1);
            node.c1 = c1;
            v1 = -negamax(b1, c1, depth - 1, !aiTurn);
            c1.score = v1;
        }
        if (!board.colFull(2)) {
            Board b2 = new Board(board);
            if (aiTurn)
                b2.dropPiece(2, aiPiece);
            else
                b2.dropPiece(2, playerPiece);
            Node c2 = new Node(v2);
            node.c2 = c2;
            v2 = -negamax(b2, node.c2, depth - 1, !aiTurn);
            c2.score = v2;
        }
        if (!board.colFull(3)) {
            Board b3 = new Board(board);
            if (aiTurn)
                b3.dropPiece(3, aiPiece);
            else
                b3.dropPiece(3, playerPiece);
            Node c3 = new Node(v3);
            node.c3 = c3;
            v3 = -negamax(b3, node.c3, depth - 1, !aiTurn);
            c3.score = v3;
        }
        if (!board.colFull(4)) {
            Board b4 = new Board(board);
            if (aiTurn)
                b4.dropPiece(4, aiPiece);
            else
                b4.dropPiece(4, playerPiece);
            Node c4 = new Node(v4);
            node.c4 = c4;
            v4 = -negamax(b4, node.c4, depth - 1, !aiTurn);
            c4.score = v4;
        }
        if (!board.colFull(5)) {
            Board b5 = new Board(board);
            if (aiTurn)
                b5.dropPiece(5, aiPiece);
            else
                b5.dropPiece(5, playerPiece);
            Node c5 = new Node(v5);
            node.c5 = c5;
            v5 = -negamax(b5, node.c5, depth - 1, !aiTurn);
            c5.score = v5;
        }
        if (!board.colFull(6)) {
            Board b6 = new Board(board);
            if (aiTurn)
                b6.dropPiece(6, aiPiece);
            else
                b6.dropPiece(6, playerPiece);
            Node c6 = new Node(v6);
            node.c6 = c6;
            v6 = -negamax(b6, node.c6, depth - 1, !aiTurn);
            c6.score = v6;
        }
        
        // Get the best score.
        if (!board.colFull(0))
            best = Integer.max(best, v0);
        if (!board.colFull(1))
            best = Integer.max(best, v1);
        if (!board.colFull(2))
            best = Integer.max(best, v2);
        if (!board.colFull(3))
            best = Integer.max(best, v3);
        if (!board.colFull(4))
            best = Integer.max(best, v4);
        if (!board.colFull(5))
            best = Integer.max(best, v5);
        if (!board.colFull(6))
            best = Integer.max(best, v6);
        
        // Get out with the best score.
        return best;
    }
    
    // Gets the best column based on a given score.
    private int getCol (int best) {
        if (root.c0 != null) {
            if (root.c0.score == best)
                return 0;
        }
        if (root.c1 != null) {
            if (root.c1.score == best)
                return 1;
        }
        if (root.c2 != null) {
            if (root.c2.score == best)
                return 2;
        }
        if (root.c3 != null) {
            if (root.c3.score == best)
                return 3;
        }
        if (root.c4 != null) {
            if (root.c4.score == best)
                return 4;
        }
        if (root.c5 != null) {
            if (root.c5.score == best)
                return 5;
        }
        if (root.c6 != null) {
            if (root.c6.score == best)
                return 6;
        }
        
        return -1;
    }
    
    // High-level evaluation function.
    private int calcScore (Board board) {
        int aiScore = verticalScore(board, aiPiece) + horizontalScore(board, aiPiece) + 
                      leadingDiagonalScore(board, aiPiece) + trailingDiagonalScore(board, aiPiece);
        int playerScore = verticalScore(board, playerPiece) + horizontalScore(board, playerPiece) + 
                          leadingDiagonalScore(board, playerPiece) + trailingDiagonalScore(board, playerPiece);
        
        return aiScore - playerScore;
    }
    
    // Count number of different segments vertically and get aggregate score.
    private int verticalScore (Board board, char piece) {
        int count = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for (int i = 0; i < Board.COLS; i++) {
            for (int j = 0; j < Board.ROWS; j++) {
                if (board.getCell(j, i) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                }
            }
        }
        
        return segmentScore(one, two, three, four);
    }
    
    // Count number of different segments horizontally and get aggregate score.
    private int horizontalScore (Board board, char piece) {
        int count = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for (int i = 0; i < Board.ROWS; i++) {
            for (int j = 0; j < Board.COLS; j++) {
                if (board.getCell(i, j) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                }
            }
        }
        
        return segmentScore(one, two, three, four);
    }
    
    // Count number of different segments top-left to bottom-right diagonally and get aggregate score.
    private int leadingDiagonalScore (Board board, char piece) {
        int count = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for (int i = 0, x = 0; i < Board.ROWS; i++, x++) {
            for (int j = x, k = 0; j < Board.ROWS && k < Board.COLS; j++, k++) {
                if (board.getCell(j, k) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                    
                    if (board.getCell(j, k) == Board.NULL_CHAR) {
                        break;
                    }
                }
            }
        }
        
        count = 0;
        
        for (int i = 0, y = 1; i < Board.COLS; i++, y++) {
            for (int j = 0, k = y; j < Board.ROWS && k < Board.COLS; j++, k++) {
                if (board.getCell(j, k) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                    
                    if (board.getCell(j, k) == Board.NULL_CHAR) {
                        break;
                    }
                }
            }
        }
        
        return segmentScore(one, two, three, four);
    }
    
    // Count number of different segments top-right to bottom-left diagonally and get aggregate score.
    private int trailingDiagonalScore (Board board, char piece) {
        int count = 0;
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;

        for (int i = 0, x = 0; i < Board.ROWS; i++, x++) {
            for (int j = x, k = Board.COLS - 1; j < Board.ROWS && k >= 0; j++, k--) {
                if (board.getCell(j, k) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                    
                    if (board.getCell(j, k) == Board.NULL_CHAR) {
                        break;
                    }
                }
            }
        }
        
        count = 0;
        
        for (int i = 0, y = Board.COLS - 2; i < Board.COLS; i++, y--) {
            for (int j = 0, k = y; j < Board.ROWS && k >= 0; j++, k--) {
                if (board.getCell(j, k) == piece) {
                    count++;
                } else {
                    if (count == 1)
                        one++;
                    else if (count == 2)
                        two++;
                    else if (count == 3)
                        three++;
                    else if (count == 4)
                        four++;
                    
                    count = 0;
                    
                    if (board.getCell(j, k) == Board.NULL_CHAR) {
                        break;
                    }
                }
            }
        }
        
        return segmentScore(one, two, three, four);
    }
    
    // Gives a 500 score for each 4-segment, 50 score for each 3-segment, 10 score for each 2-segment, and 1 score for each 1-segment.
    private int segmentScore (int one, int two, int three, int four) {
        return (500 * four) + (50 * three) + (10 * two) + (1 * one);
    }
}
