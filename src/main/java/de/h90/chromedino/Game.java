package de.h90.chromedino;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game {

    private static final int X_OFFSET = 30;

    private static Dinosaur DINO = new Dinosaur();

    private static String[] lines = new String[]{
            "                __",
            "               /*_)",
            "     .---..._./..",
            "    /...........",
            " __/(...|.(...|",
            "/__.-|_|---|_|"};

    public static void main(String[] args) {
        final String title = "Press Shift to jump";
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

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 16) {
                    DINO.jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        Font font = new Font(Font.MONOSPACED, canvas.getFont().getStyle(), canvas.getFont().getSize());

        new Timer(25, e -> {

            BufferStrategy bufferStrategy = canvas.getBufferStrategy();
            Graphics graphics = bufferStrategy.getDrawGraphics();
            graphics.clearRect(0, 0, width, height);
            graphics.setColor(Color.GREEN);
            int base = DINO.nextHeight();
            for (int i = 0; i < lines.length; i++) {
                String line = lines[i];
                graphics.setFont(font);
                graphics.drawString(line, X_OFFSET, base + i * 10);
            }
            bufferStrategy.show();
            graphics.dispose();
        }).start();
    }
}
