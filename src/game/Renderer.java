
package game;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * Renderer.java
 * Effectue le rendu
 */
public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch (Runner.state) {
            case GAME:
            case OVER:
                Runner.game.render(g);
                System.out.println("rendered game/over");
                break;
            case MENU:
                Runner.menu.render(g);
                System.out.println("menu");
                break;
            case HISCORE:
                break;
        }
        
    }
    
    
}
