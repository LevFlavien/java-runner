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
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (Runner.state == Runner.STATE.GAME) {
            Runner.game.mousePressed();
        } else {
            
            // -offset
            int x = e.getX()-3;
            int y = e.getY()-22;
            //System.out.println("x: "+x+"; y: "+y);
            
            //play
            if (Menu.playButton.contains(x, y)){
                Runner.state = Runner.STATE.GAME;
                Runner.start();
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
