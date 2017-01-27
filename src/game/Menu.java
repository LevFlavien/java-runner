/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author flevesque
 */
public class Menu {
    
    public static Rectangle playButton = new Rectangle(Game.WIDTH / 2, 200, 100, 50);
    public static Rectangle helpButton = new Rectangle(Game.WIDTH / 2, 300, 100, 50);
    public static Rectangle quitButton = new Rectangle(Game.WIDTH / 2, 400, 100, 50);
    
    public void render(Graphics g) {
        
        Graphics2D g2d = (Graphics2D) g;
        
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        //g.
        g.drawString("RUNNER", Game.WIDTH / 2 - 60, 100);
        
        g2d.setColor(Color.blue);
        g.setFont(fnt0.deriveFont(38f));
        g.drawString("Play", playButton.x+10, playButton.y+40);
        g.drawString("Help", helpButton.x+10, helpButton.y+40);
        g.drawString("Quit", quitButton.x+10, quitButton.y+40);
        g2d.draw(playButton);
        g2d.draw(helpButton);
        g2d.draw(quitButton);
    }
}
