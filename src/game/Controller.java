
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * Controller.java
 * Controleur
 * Génère les obstacles et powerups
 */
public class Controller {
    
    Game game;
    
    public ArrayList<Rectangle> columns = new ArrayList<>();
    public Random rand = new Random();
    
    public Controller(Game game) {
        this.game = game;
    }
    
    public void addColumn(boolean start) {
        //int space = 300;
        int width = 10;
        int height = 10 + rand.nextInt(80);
        
        
        if (start)
        {
            columns.add(new Rectangle(Runner.WIDTH + width + columns.size() * 900, Runner.HEIGHT - height - 140, width, height));
            //columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size()-1).x + 600, Runner.HEIGHT - height - 120, width, height));
            //columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width+100, HEIGHT - height - space));
        }
    }
      
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
}
