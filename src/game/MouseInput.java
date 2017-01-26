/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author flevesque
 */
public class MouseInput implements MouseListener {
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    //public Rectangle playButton = new Rectangle(Game.WIDTH / 2 + 120, 200, 100, 50);
  //  public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 + 120, 300, 100, 50);
    //public Rectangle quitButton = new Rectangle(Game.WIDTH / 2 + 120, 400, 100, 50);
    @Override
    public void mousePressed(MouseEvent e) {
        if (Game.state == Game.STATE.GAME) {
            Game.runner.mousePressed();
        } else {
            //play
            if (e.getX() >= Game.WIDTH / 2 + 120 && e.getY() <= Game.WIDTH / 2 + 120) {
                Game.state = Game.STATE.GAME;
            }
        }        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
