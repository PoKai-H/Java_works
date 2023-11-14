package core;

import edu.princeton.cs.algs4.StdDraw;
import tileengine.TERenderer;
import tileengine.TETile;

public class Engine {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 50;

    public TETile[][] createWorldWithSeedFromInput(Long seed) {
        World world = new World(WIDTH, HEIGHT, seed);
        return world.getTiles();
    }
    public void playWithKeyboard() {
        mainMenu();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (key == 'N' || key == 'n') {
                    enterSeedAndCreateWorld();
                    break; // Break out of the loop after world creation
                } else if (key == 'L' || key == 'l') {
                    // Code to load an existing game
                    break; // Break out of the loop after loading the game
                } else if (key == 'Q' || key == 'q') {
                    System.exit(0); // Quit the application
                }
            }
            StdDraw.pause(100); // Add a short delay to prevent a busy loop
        }
    }

    private void mainMenu() {
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.clear(StdDraw.BLACK);

        // Display the menu options
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(256, 350, "CS61B: THE GAME");
        StdDraw.text(256, 250, "New Game (N)");
        StdDraw.text(256, 200, "Load Game (L)");
        StdDraw.text(256, 150, "Quit (Q)");
        StdDraw.show();
    }

    private void enterSeedAndCreateWorld() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(256, 100, "Enter Seed: ");
        StdDraw.show();

        String seed = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char seedChar = StdDraw.nextKeyTyped();
                if (seedChar == 'S' || seedChar == 's') {
                    break; // Break out of the loop when 'S' is pressed
                } else if (Character.isDigit(seedChar)) {
                    seed += seedChar;
                    StdDraw.clear(StdDraw.BLACK);
                    StdDraw.text(256, 100, "Enter Seed: " + seed);
                    StdDraw.show();
                }
            }
            StdDraw.pause(100);
        }
        createWorldWithSeed(seed);
    }

    private void createWorldWithSeed(String seed) {
        Long randomSeed = Long.parseLong(seed); // Convert seed to an integer
        World world = new World(WIDTH, HEIGHT, randomSeed);
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        // render the world to the screen
        ter.renderFrame(world.getTiles());
    }

}
