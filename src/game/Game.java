package game;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Poulet
 */

class Game implements Runnable {

    private final Controller c;
    private final Player player;
    private int ticks;
    private int score;

    public Game() {

        c = new Controller();
        player = new Player(Runner.WIDTH / 3, Runner.HEIGHT / 2, 20, 40);
        score = 0;

        for (int i = 0; i < 5; i++) {
            c.addColumn(true);
        }
    }

    @Override
    public void run() {
        int speed = 10;
        System.out.println("Game.run()");

        while (Runner.state == Runner.STATE.GAME) {
            ticks++;

            for (int i = 0; i < c.columns.size(); i++) {
                Rectangle column = c.columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && player.ymotion < 15) {
                player.ymotion += 2;
            }

            for (int i = 0; i < c.columns.size(); i++) {
                Rectangle column = c.columns.get(i);

                if (column.x + column.width < 0) {
                    c.columns.remove(column);
                    c.addColumn(false);

                    if (column.y == 0) {
                        c.addColumn(false);
                    }
                }
            }

            // actualise la position
            player.y += player.ymotion;

            for (Rectangle column : c.columns) {
                if (column.intersects(player)) {
                    Runner.state = Runner.STATE.OVER;

                    player.x = column.x - player.width;
                }
            }

            // stoppe player au niveau du sol
            if (player.y >= Runner.HEIGHT - 160 || player.y < 0) {
                player.y = Runner.HEIGHT - 160;
            }

            // réinitialise le double saut quand player atterit
            if (player.y == 440) {
                player.jumping = 0;
            }
            System.out.println(player.y);
            // actualise le rendu
            Runner.renderer.repaint();

            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        Runner.renderer.repaint();
        System.out.println("Game.run() end");
    }

    void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0, 0, Runner.WIDTH, Runner.HEIGHT);

        g.setColor(Color.orange);
        g.fillRect(0, Runner.HEIGHT - 120, Runner.WIDTH, Runner.HEIGHT - 120);

        g.setColor(Color.green);
        g.fillRect(0, Runner.HEIGHT - 120, Runner.WIDTH, 20);

        g.setColor(Color.red);
        g.fillRect(player.x, player.y, player.width, player.height);

        for (Rectangle column : c.columns) {
            c.paintColumn(g, column);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));

        if (Runner.state == Runner.STATE.OVER) {
            g.drawString("GameOver", 75, Runner.HEIGHT / 2);
            HighScore.setHighScore(score);
        }

        g.setFont(new Font("Arial", 1, 50));

        if (Runner.state == Runner.STATE.GAME) {
            score++;
        }
        String scoreString = "Score : " + score;
        g.drawString(scoreString, 20, Runner.HEIGHT - 50);
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("clicked");
        switch (Runner.state) {
            case GAME:
                System.out.println(Runner.state);
                if (e.getButton() == 1) {   // si clic droit
                    player.jump();
                } else {
                    player.crouch();
                }
                break;
            case OVER:
                System.out.println(Runner.state);
                Runner.state = Runner.STATE.MENU;
                Runner.renderer.repaint();
                break;
        }
    }

}
