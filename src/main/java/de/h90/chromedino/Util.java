package de.h90.chromedino;

import java.awt.Font;
import java.awt.Graphics;

class Util {

    static void drawText(String[] text, int x, int y, Font font, Graphics graphics) {
        for (int i = text.length - 1; i >= 0; i--) {
            String line = text[i];
            graphics.setFont(font);
            int j = i - text.length;
            graphics.drawString(line, x, y + j * 10);
        }
    }
}
