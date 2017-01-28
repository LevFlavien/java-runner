/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Poulet
 */
public class Renderer extends JPanel {

    private static final long serialVersionUID = 1L;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        switch (Runner.state) {
            case GAME:
                Runner.game.render(g);
                break;
            case MENU:
                Runner.menu.render(g);
                break;
        }
        
    }
    
    
}
