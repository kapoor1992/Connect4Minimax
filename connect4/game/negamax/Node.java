package connect4.game.negamax;

// Used for tree nodes.
public class Node {
    public int score;
    public Node c0;
    public Node c1;
    public Node c2;
    public Node c3;
    public Node c4;
    public Node c5;
    public Node c6;
    
    // Used for non-leaf nodes.
    public Node () {
        this.score = 0; // Indicates a non-leaf node.
        initChildren();
    }
    
    // Used for leaf nodes.
    public Node (int score) {
        this.score = score;
        initChildren();
    }
    
    // Initializes all 7 children.
    private void initChildren() {
        c0 = null;
        c1 = null;
        c2 = null;
        c3 = null;
        c4 = null;
        c5 = null;
        c6 = null;
    }
}
