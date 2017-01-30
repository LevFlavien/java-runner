package game;

import javax.swing.*;
import java.awt.*;

/**
 * Renderer.java
 * Effectue le rendu
 */
class Renderer extends JPanel {

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
