package knightworld;

import tileengine.TERenderer;
import tileengine.TETile;
import tileengine.Tileset;

/**
 * Draws a world consisting of knight-move holes.
 */
public class KnightWorld {

    private TETile[][] tiles;
    // TODO: Add additional instance variables here
    private int width;
    private int height;
    private int holeSize;

    public KnightWorld(int width, int height, int holeSize) {
        // TODO: Fill in this constructor and class, adding helper methods and/or classes as necessary to draw the
        //  specified pattern of the given hole size for a window of size width x height. If you're stuck on how to
        //  begin, look at the provided demo code!
        tiles = new TETile[width][height];
        this.width = width;
        this.height = height;
        this.holeSize = holeSize;
        for (int x = 0; x < this.width; x++) {
            for (int y = 0; y < this.height; y++) {
                tiles[x][y] = Tileset.FLOWER;
            }
        }
        generateKnightHoles();
    }

    private void generateKnightHoles() {
        int [] xs = new int[]{1, 4, 2, 0, 3};
        for (int y = 0; y < height; y++) {
            int dv = (y/holeSize) % 5;
            int firstX = xs[dv]*holeSize;
            generateHoles(firstX, y);
            generateRestHoles(firstX, y);
        }

    }
    private void generateHoles(int x, int y){
        for(int i = 0; i < holeSize; i++) {
            if (isValidPosition(x+i)) {
                tiles[x + i][y] = Tileset.NOTHING;
            }
        }
    }
    private void generateRestHoles(int firstX, int y) {
        for (int x = firstX + 5 * holeSize; x <width; x += 5 * holeSize) {
            generateHoles(x, y);
            }
        }

    private boolean isValidPosition(int x) {
        return x >= 0 && x < width;
    }

    /** Returns the tiles associated with this KnightWorld. */
    public TETile[][] getTiles() {
        return tiles;
    }

    public static void main(String[] args) {
        // Change these parameters as necessary
        int width = 60;
        int height = 40;
        int holeSize = 4;

        KnightWorld knightWorld = new KnightWorld(width, height, holeSize);

        TERenderer ter = new TERenderer();
        ter.initialize(width, height);
        ter.renderFrame(knightWorld.getTiles());

    }
}
