public class Board {
    private int[][] board;
    public Board(int[][] blocks) {
        int n = blocks.length;
        board = blocks;
    }

    public int dimension() {
        return board.length;
    }

    public int hamming() {
        int moves = 0;
        int currIdx;
        boolean skip = false;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                currIdx = (((i) * dimension()) + (j+1));
                if (currIdx == dimension() * dimension()) break;
                if (board[i][j] != currIdx) moves++;
            }
        }
        return moves;
    }

    public int manhattan() {
        //TODO Implement
        return 1;
    }

    public boolean isGoal() {
        //TODO Implement
        return false;
    }

    public boolean equals(Object y) {
        return true;
    }

    /*
    public Board twin() {
    }

    public Iterable<Board> neighbors() {
    }
    */

    public String toString() {
        //TODO Implement
        return "";
    }

    public static void main(String[] args) {
        int[][] init = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board b = new Board(init);
        System.out.println(b.hamming());

    }

}
