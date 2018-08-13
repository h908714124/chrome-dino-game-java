package de.h90.chromedino;

class Dinosaur {

    private static final int INITIAL_HEIGHT = 520;

    private static final double GRAVITY = 0.060;

    private int epoch;

    private float velocity = 0;

    private int y = INITIAL_HEIGHT;

    void jump() {
        if (y == INITIAL_HEIGHT) {
            velocity = 6f;
        }
    }

    int nextHeight() {
        if (velocity == 0) {
            return y;
        }
        epoch++;
        velocity -= GRAVITY;
        y = (int) (INITIAL_HEIGHT - (velocity * epoch));
        if (y > INITIAL_HEIGHT) {
            y = INITIAL_HEIGHT;
            epoch = 0;
            velocity = 0;
        }
        return y;
    }

}
