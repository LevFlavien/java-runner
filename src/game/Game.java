/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game implements Runnable {
    
    public int ticks, score;
    
    public Controller c;
    
    public Player player;
    
    public static boolean gameOver;
    
    public int timestamp = 0;
    
    public Game() {
        
        System.out.println("init");
        
        c = new Controller(this);
        
        player = new Player(Runner.WIDTH/3, Runner.HEIGHT/2, 20, 20);
        
        score = 0;
        
        System.out.println("test");
        
        for (int i = 0; i<5; i++)
        {
            c.addColumn(true);
        }
    }
    
    @Override
    public void run() {
        
        int speed = 10;
        
        while (Runner.state == Runner.STATE.GAME) {
            System.out.println("run");
            
            ticks++;
            
            for (int i = 0; i < c.columns.size(); i++) {
                Rectangle column = c.columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && player.ymotion < 15){
                player.ymotion += 2;
            }

            for (int i = 0; i < c.columns.size(); i++) {
                Rectangle column = c.columns.get(i);

                if (column.x + column.width < 0) {
                    c.columns.remove(column);
                    c.addColumn(false);

                    if(column.y == 0) {
                        c.addColumn(false);
                    }
                }
            }

            player.y += player.ymotion;

            for (Rectangle column : c.columns) {
                if (column.intersects(player)) {
                    gameOver = true;
                    
                    player.x = column.x - player.width;
                }
            }
            
            if (player.y >= Runner.HEIGHT - 140 || player.y < 0) {
                player.y = Runner.HEIGHT-140;
            }
            if (gameOver) {
                player.y = Runner.HEIGHT - 120 - player.height;
            }
            
            //réinitialise le double saut quand player atterit
            if (player.y == 460) {
                player.jumping = 0;
            }
            
            timestamp++;
            
            Runner.renderer.repaint();
            
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0,0,Runner.WIDTH,Runner.HEIGHT);
        
        g.setColor(Color.orange);
        g.fillRect(0, Runner.HEIGHT-120, Runner.WIDTH, Runner.HEIGHT-120);
        
        g.setColor(Color.green);
        g.fillRect(0, Runner.HEIGHT-120, Runner.WIDTH, 20);
        
        g.setColor(Color.red);
        g.fillRect(player.x, player.y, player.width, player.height);
        
        for (Rectangle column : c.columns) {
            c.paintColumn(g, column);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));
        
        if (gameOver) {
            g.drawString("GameOver", 75, Runner.HEIGHT/2);
        }
        if (Runner.state != Runner.STATE.GAME) {
            //menu.render(g);
            g.drawString("Click to start", 75, Runner.HEIGHT/2);
        }
        
        g.setFont(new Font("Arial", 1, 50));
        
        if(Runner.state == Runner.STATE.GAME && !gameOver) {
            score++;
        }
        String scoreString = "Score : "+ score;
        g.drawString(scoreString, 20, Runner.HEIGHT-50);
    }
    
    public void mousePressed() {
        player.jump();
    }
    
}
