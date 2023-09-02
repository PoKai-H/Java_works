package game2048;

import org.apache.commons.lang3.ObjectUtils;

import java.util.Formatter;


/** The state of a game of 2048.
 *  @author P. N. Hilfinger + Josh Hug
 */
public class Model {
    /** Current contents of the board. */
    private final Board board;
    /** Current score. */
    private int score;
    /** Maximum score so far.  Updated when game ends. */
    private int maxScore;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        board = new Board(size);
        score = maxScore = 0;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore) {
        board = new Board(rawValues);
        this.score = score;
        this.maxScore = maxScore;
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. */
    public Tile tile(int col, int row) {
        return board.tile(col, row);
    }

    /** Return the number of squares on one side of the board. */
    public int size() {
        return board.size();
    }

    /** Return the current score. */
    public int score() {
        return score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        score = 0;
        board.clear();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        board.addTile(tile);
        checkGameOver();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        return maxTileExists(board) || !atLeastOneMoveExists(board);
    }

    /** Checks if the game is over and sets the maxScore variable
     *  appropriately.
     */
    private void checkGameOver() {
        if (gameOver()) {
            maxScore = Math.max(score, maxScore);
        }
    }
    
    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     * */
    public static boolean emptySpaceExists(Board b) {
        // TODO: Fill in this function.
        for (int i=0; i<b.size();i++){
            for (int j=0; j<b.size(); j++){
                if (b.tile(i,j) == null){
                    return true ;
                }
            }
        }


        return false;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by this.MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        // TODO: Fill in this function.
        for(int i=0; i<b.size(); i++){
            for (int j=0; j<b.size(); j++){
                if(b.tile(i,j)== null) continue;
                if(b.tile(i,j).value() == MAX_PIECE){
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */


    public static boolean atLeastOneMoveExists(Board b) {
        // TODO: Fill in this function.
        for (int i = 0; i <= b.size() - 1; i++) {
            for (int j = 0; j <= b.size() - 1; j++) {
                if (b.tile(i, j) == null) {
                    return true;
                }
                if (j+1 < b.size() && b.tile(i, j + 1) != null && b.tile(i, j).value() == b.tile(i, j + 1).value()) {
                    return true;
                }
                if (i+1 < b.size() && b.tile(i + 1, j) != null && b.tile(i, j).value() == b.tile(i + 1, j).value()) {
                    return true;
                }

            }

        }return false;
    }
    /** Tilt the board toward SIDE.
     *
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     * */
    public void tilt(Side side) {
        // TODO: Modify this.board (and if applicable, this.score) to account
        // for the tilt to the Side SIDE.
        if (side == Side.NORTH) {
            for (int j = 0; j < board.size(); j++) {
                Tile t = board.tile(j, 0);
                tiltColumn(j, t, side);
            }
        } else if (side == Side.EAST) {
            for (int j = board.size() - 1; j > -1; j--) {
                Tile t = board.tile(0, j);
                tiltColumn(j, t, side);
            }
        } else if (side == Side.WEST) {
            for (int j = 0; j< board.size(); j++) {
                Tile t = board.tile(0,j);
                tiltColumn(j,t, side);
            }
        }
        else {
            for(int j = board.size()-1;j >-1;j--){
                Tile t = board.tile(j,0);
                tiltColumn(j, t, side);
            }
        }



            checkGameOver();
        }


    private int moveTileFarUpAsPossible(Tile t, int k){
        if(t == null){
            return k;
        }
        int col = t.col();
        int row = t.row()+1;

        for (int i=row; i<board.size(); i++){
            if(board.tile(col,i)==null && i!=3){
                continue;
            }
            else if(board.tile(col,i)==null&&i == 3){
                board.move(col, i, t);
                return 0;
            }
            else if(board.tile(col,i).value()==t.value()){
                if(k == 0) {
                    board.move(col, i, t);
                    score += board.tile(col, i).value();
                    return i;
                }
                else{
                    board.move(col, i-1, t);
                    return 0;
                }
            }
            else if(board.tile(col,i) != null) {
                board.move(col, i -1, t);
                return 0;
            }

            System.out.println(i);
        }
        return 0;
    }

    private int moveTileFarUpAsPossibleEast(Tile t, int k) {
        if (t == null) {
            return k;
        }
        int col = t.col()+1;
        int row = t.row() ; // 向上移動一格

        for (int i = col; i < board.size(); i++) { // 在新視角下遍歷列
            if (board.tile(i, row) == null && i != board.size() - 1) {
                continue;
            } else if (board.tile(i, row) == null && i == board.size() - 1) {
                board.move(i, row, t);
                return 0;
            } else if (board.tile(i, row).value() == t.value()) {
                if (k == 0) {
                    board.move(i, row, t);
                    score += board.tile(i, row).value();
                    return i;
                } else {
                    board.move(i - 1, row, t);
                    return 0;
                }
            } else if (board.tile(i, row) != null) {
                board.move(i - 1, row, t);
                return 0;
            }
        }
        return 0;
    }
    private int moveTileFarUpAsPossibleSouth(Tile t, int k) {
        if (t == null) {
            return k;
        }
        int col = t.col();
        int row = t.row() - 1; // 向下移動一格

        for (int i = row; i >= 0; i--) { // 在新視角下向下遍歷行
            if (board.tile(col, i) == null && i != 0) {
                continue;
            } else if (board.tile(col, i) == null && i == 0) {
                board.move(col, i, t);
                return 0;
            } else if (board.tile(col, i).value() == t.value()) {
                if (k == 0) {
                    board.move(col, i, t);
                    score += board.tile(col, i).value();
                    return i+1;
                } else {
                    board.move(col, i + 1, t);
                    return 0;
                }
            } else if (board.tile(col, i) != null) {
                board.move(col, i + 1, t);
                return 0;
            }
        }
        return 0;
    }
    private int moveTileFarUpAsPossibleWest(Tile t, int k) {
        if (t == null) {
            return k;
        }
        int col = t.col();
        int row = t.row();

        for (int i = col - 1; i >= 0; i--) { // 在新视角下向左遍历列
            if (board.tile(i, row) == null && i != 0) {
                continue;
            } else if (board.tile(i, row) == null && i == 0) {
                board.move(i, row, t);
                return 0;
            } else if (board.tile(i, row).value() == t.value()) {
                if (k == 0) {
                    board.move(i, row, t);
                    score += board.tile(i, row).value();
                    return i+1;
                } else {
                    board.move(i + 1, row, t);
                    return 0;
                }
            } else if (board.tile(i, row) != null) {
                board.move(i + 1, row, t);
                return 0;
            }
        }
        return 0;
    }


    private void tiltColumn(int j,Tile t, Side side){
        int k = 0;
        if (side == Side.NORTH){
            for(int i = board.size()-2; i >-1; i--) {
                t = board.tile(j, i);
                k = moveTileFarUpAsPossible(t, k);
                System.out.println(board);

            }
        } else if (side == Side.EAST) {
            for(int i = board.size()-2; i >-1; i--) {
                t = board.tile(i, j);
                k = moveTileFarUpAsPossibleEast(t, k);
                System.out.println(board);

            }
        } else if (side == Side.WEST){
            for(int i = 1; i < board.size();i++){
                t = board.tile(i,j);
                k = moveTileFarUpAsPossibleWest(t, k);
                System.out.println(board);
            }
        } else {
            for (int i=1; i< board.size();i++){
                t = board.tile(j,i);
                k = moveTileFarUpAsPossibleSouth(t,k);
                System.out.println(board);
            }
        }

    }


    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}

