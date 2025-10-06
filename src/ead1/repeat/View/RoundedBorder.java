package ead1.repeat.View;

import java.awt.*;
import javax.swing.border.Border;

class RoundedBorder implements Border {
    private int radius;

    RoundedBorder(int radius) {
        this.radius = radius;
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(radius+1, radius+1, radius+2, radius+1);
    }

    @Override
    public boolean isBorderOpaque() {
        return false; // better for smooth corners
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // 🔹 smooth edges
        g2.setColor(Color.BLACK); // border color
        g2.setStroke(new BasicStroke(2)); // border thickness
        g2.drawRoundRect(x, y, width-1, height-1, radius, radius);
        g2.dispose();
    }
}
