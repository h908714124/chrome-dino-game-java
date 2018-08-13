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

public class Game {

    final static int WIDTH = 1200;

    final static int HEIGHT = WIDTH / 16 * 9;

    public static void main(String[] args) {
        final String title = "Press Shift to jump";

        //Creating the frame.
        JFrame frame = new JFrame(title);

        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //Creating the canvas.
        Canvas canvas = new Canvas();

        canvas.setSize(WIDTH, HEIGHT);
        canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        canvas.setFocusable(false);

        //Putting it all together.
        frame.add(canvas);

        canvas.createBufferStrategy(3);

        Font font = new Font(Font.MONOSPACED, canvas.getFont().getStyle(), canvas.getFont().getSize());
        Dinosaur dino = new Dinosaur(font);
        Cactus cactus = new Cactus(font);

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 16) {
                    dino.jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Random random = ThreadLocalRandom.current();

        new Timer(25, e -> {

            if (random.nextInt(30) == 0) {
                cactus.start();
            }

            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, WIDTH, HEIGHT);
            graphics.setColor(Color.GREEN);
            dino.draw(graphics);
            cactus.draw(graphics);
            Toolkit.getDefaultToolkit().sync();
            bufferStrategy.show();
            graphics.dispose();
        }).start();
    }
}
