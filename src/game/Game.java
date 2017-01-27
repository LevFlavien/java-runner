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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Poulet
 */
public class Game implements ActionListener {
    
    public static Game runner;
    
    public static Menu menu;
    
    public static final int WIDTH = 1300, HEIGHT = 600;
    
    public Renderer renderer;
    
    public Player player;
    
    public int ticks, score;
    
    public Controller c;
    
    public static boolean gameOver;
    
    public int timestamp = 0;
    
    public static enum STATE {
        MENU,
        GAME,
    };
    public static STATE state = STATE.MENU;
    
    public Game(Object object) {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);
        
        renderer = new Renderer();
        
        menu = new Menu();
        
        c = new Controller(runner);
        
        jframe.add(renderer);
        
        jframe.addMouseListener(new MouseInput());
        
        jframe.setTitle("Runner");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
        player = new Player(WIDTH/3, HEIGHT/2, 20, 20);
        
        score = 0;
        
        for (int i = 0; i<5; i++)
        {
            c.addColumn(true);
        }
        timer.start();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int speed = 10;
        
        if (state == STATE.GAME) {
            
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
            
            if (player.y >= HEIGHT - 140 || player.y < 0) {
                player.y = HEIGHT-140;
            }
            if (gameOver) {
                player.y = HEIGHT - 120 - player.height;
            }
            
            //réinitialise le double saut quand player atterit
            if (player.y == 460) {
                player.jumping = 0;
            }
            
            timestamp++;
            
            renderer.repaint();
        }
    }
    
    void render(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT-120, WIDTH, HEIGHT-120);
        
        g.setColor(Color.green);
        g.fillRect(0, HEIGHT-120, WIDTH, 20);
        
        g.setColor(Color.red);
        g.fillRect(player.x, player.y, player.width, player.height);
        
        for (Rectangle column : c.columns) {
            c.paintColumn(g, column);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));
        
        if (gameOver) {
            g.drawString("GameOver", 75, HEIGHT/2);
        }
        if (state != STATE.GAME) {
            //menu.render(g);
            g.drawString("Click to start", 75, HEIGHT/2);
        }
        
        g.setFont(new Font("Arial", 1, 50));
        
        if(state == STATE.GAME && !gameOver) {
            score++;
        }
        String scoreString = "Score : "+ score;
        g.drawString(scoreString, 20, HEIGHT-50);
    }
    
    public static void main(String[] args) {
        runner = new Game(args);
    }
    
    public void mousePressed() {
        switch (Game.state) {
            case GAME:
                player.jump();
                break;
            case MENU:
                //Game.menu.render(g);
                break;
        }
    }
}
