package de.h90.chromedino;

import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game {

    private static Dinosaur DINO = new Dinosaur();

    public static void main(String[] args) {
        final String title = "Test Window";
        final int width = 1200;
        final int height = width / 16 * 9;

        //Creating the frame.
        JFrame frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //Creating the canvas.
        Canvas canvas = new Canvas();

        canvas.setSize(width, height);
        canvas.setBackground(Color.BLACK);
        canvas.setVisible(true);
        canvas.setFocusable(false);

        //Putting it all together.
        frame.add(canvas);

        canvas.createBufferStrategy(3);

        int[] y = new int[]{600};
        Font font = new Font(Font.MONOSPACED, canvas.getFont().getStyle(), canvas.getFont().getSize());

        new Timer(25, e -> {
            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, width, height);
            graphics.setColor(Color.GREEN);
            int base = y[0]--;
            for (int i = 0; i < DINO.lines.length; i++) {
                String line = DINO.lines[i];
                graphics.setFont(font);
                graphics.drawString(line, 5, base + i * 10);
            }
            bufferStrategy.show();
            graphics.dispose();
        }).start();
    }
}
