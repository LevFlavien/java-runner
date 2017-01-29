
package game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * MouseInput.java
 * 
 */
public class MouseInput implements MouseListener {
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (Runner.state == Runner.STATE.MENU) {
            System.out.println(Runner.state);
                // -offset
                int x = e.getX()-3;
                int y = e.getY()-22;
                //System.out.println("x: "+x+"; y: "+y);

                //play
                if (Menu.playButton.contains(x, y)){
                    Runner.state = Runner.STATE.GAME;
                    Runner.start();
                } else if (Menu.quitButton.contains(x,y)) {
                    Runner.quit();
                }
        } else {
            Runner.game.mousePressed(e);
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
