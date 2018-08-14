package de.h90.chromedino;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends JFrame {

    static final int WIDTH = 1200;

    private static final Random RANDOM = ThreadLocalRandom.current();

    private static final Canvas CANVAS = new Canvas();

    private static final int HEIGHT = WIDTH / 16 * 9;
    private static final int KEYCODE_SHIFT = 16;
    private static final int KEYCODE_ESCAPE = 27;
    private static final int MAX_CACTI = 2;
    private static final int MIN_CACTUS_DISTANCE = 260;
    private static final int KEYCODE_SPACE = 32;
    private static final int KEYCODE_P = 80;

    private void run() {
        String title = "Press Shift to jump";

        //Creating the frame.
        setTitle(title);

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        //Creating the canvas.

        CANVAS.setSize(WIDTH, HEIGHT);
        CANVAS.setBackground(Color.BLACK);
        CANVAS.setVisible(true);
        CANVAS.setFocusable(false);

        //Putting it all together.
        add(CANVAS);

        CANVAS.createBufferStrategy(3);

        Font font = new Font(Font.MONOSPACED, CANVAS.getFont().getStyle(), CANVAS.getFont().getSize());
        Dinosaur dino = new Dinosaur(font);

        Cactus[] cacti = new Cactus[MAX_CACTI];
        cacti[0] = new Cactus(font);
        cacti[1] = new Cactus(font);

        Timer timer = new Timer(25, __ -> {

            startCacti(cacti);
            BufferStrategy bufferStrategy = CANVAS.getBufferStrategy();
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, WIDTH, HEIGHT);
            graphics.setColor(Color.GREEN);
            drawEverything(dino, cacti, graphics);
            bufferStrategy.show();
            graphics.dispose();
            Toolkit.getDefaultToolkit().sync();
        });

        addKeyListener(createKeyListener(dino, timer));

        timer.start();
    }

    public static void main(String[] args) {
        new Game().run();
    }

    private static KeyListener createKeyListener(Dinosaur dino, Timer timer) {
        return new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KEYCODE_SHIFT || code == KEYCODE_SPACE) {
                    dino.jump();
                } else if (code == KEYCODE_ESCAPE) {
                    System.exit(0);
                } else if (code == KEYCODE_P) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        };
    }

    private static void drawEverything(Dinosaur dino, Cactus[] cacti, Graphics graphics) {
        dino.draw(graphics);
        for (Cactus cactus : cacti) {
            cactus.draw(graphics);
        }
    }

    private static void startCacti(Cactus[] cacti) {
        for (int i = 0; i < cacti.length; i++) {
            Cactus cactus = cacti[i];
            if (cactus.distance(cacti[(i + 1) % MAX_CACTI]) > MIN_CACTUS_DISTANCE) {
                if (RANDOM.nextInt(80) == 0) {
                    cactus.start();
                }
            }
        }
    }
}
