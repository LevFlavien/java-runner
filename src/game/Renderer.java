package game;

import javax.swing.*;
import java.awt.*;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Renderer.java
 * Effectue le rendu
 */
class Renderer extends JPanel implements Runnable {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (Runner.state) {
            case GAME:
            case OVER:
                Runner.game.render(g);
                break;
            case MENU:
                Runner.menu.render(g);
                break;
            case HISCORE:
                break;
        }

    }

    @Override
    public void run() {
        while (true) {
            System.out.println("GET REPAINTED M8");
            this.repaint();
            try {
                sleep(36);
            } catch (InterruptedException ex) {
                Logger.getLogger(Renderer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
