package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Controller.java
 * Controleur
 * Génère les obstacles et powerups
 */
class Controller {

    public final ArrayList<Rectangle> columns = new ArrayList<>();
    private final Random rand = new Random();

    public Controller() {
    }

    public void addColumn(boolean start) {
        int width = 10;
        int height = 10 + rand.nextInt(80);

        int r_height = Runner.HEIGHT - height - (start ? 140 : 120);
        int r_width = start ? Runner.WIDTH + width + columns.size() * 900 : columns.get(columns.size() - 1).x + 600;

        columns.add(new Rectangle(r_width, r_height, width, height));

    }

    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
}
