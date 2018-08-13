package de.h90.chromedino;

import java.awt.Font;
import java.awt.Graphics;

import static de.h90.chromedino.Dinosaur.BASELINE_HEIGHT;

class Cactus {

    // from asciiart.website
    private static final String[] LINES = new String[]{
            "   _  _",
            "  | || | _",
            " -| || || |",
            "  | || || |-",
            "   \\_  || |",
            "     |  _/",
            "    -| | \\",
            "     |_|-"
    };

    private static final int W = 100;

    private static final int INITIAL_X = Game.WIDTH + W;

    private float velocity = 0;

    private int x = INITIAL_X;

    private int epoch;

    private Font font;

    Cactus(Font font) {
        this.font = font;
    }

    void start() {
        if (velocity == 0) {
            x = INITIAL_X;
            velocity = 3;
        }
    }

    void draw(Graphics graphics) {
        int x = nextX();
        Util.drawText(LINES, x, BASELINE_HEIGHT, font, graphics);
    }

    private int nextX() {
        if (velocity == 0) {
            return x;
        }
        epoch++;
        x = (int) (INITIAL_X - (velocity * epoch));
        if (x < -W) {
            // stop
            epoch = 0;
            velocity = 0;
        }
        return x;
    }
}
