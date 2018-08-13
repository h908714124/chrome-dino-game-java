package de.h90.chromedino;

import java.awt.Font;
import java.awt.Graphics;

class Dinosaur {

    private static final int X_OFFSET = 60;

    // from asciiart.website
    private static String[] LINES = new String[]{
            "                __",
            "               /*_)",
            "     .---..._./..",
            "    /...........",
            " __/(...|.(...|",
            "/__.-|_|---|_|"};

    static final int BASELINE_HEIGHT = 500;

    private static final double GRAVITY = 0.060;

    private int epoch;

    private float velocity = 0;

    private int y = BASELINE_HEIGHT;

    private Font font;

    Dinosaur(Font font) {
        this.font = font;
    }

    void jump() {
        if (y == BASELINE_HEIGHT) {
            velocity = 6f;
        }
    }

    void draw(Graphics graphics) {
        int y = nextHeight();
        Util.drawText(LINES, X_OFFSET, y, font, graphics);
    }

    private int nextHeight() {
        if (velocity == 0) {
            return y;
        }
        epoch++;
        velocity -= GRAVITY;
        y = (int) (BASELINE_HEIGHT - (velocity * epoch));
        if (y > BASELINE_HEIGHT) {
            // stop
            y = BASELINE_HEIGHT;
            epoch = 0;
            velocity = 0;
        }
        return y;
    }
}
