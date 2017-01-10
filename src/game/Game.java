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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author Poulet
 */
public class Game implements ActionListener, MouseListener {
    
    public static Game runner;
    
    public final int WIDTH = 500, HEIGHT = 600;
    
    public Renderer renderer;
    
    public Rectangle man;
    
    public int ticks, ymotion, score, jumping;
    
    public ArrayList<Rectangle> columns;
    
    public Random rand;
    
    public boolean gameOver, started;
    
    public Game(Object object) {
        JFrame jframe = new JFrame();
        Timer timer = new Timer(20, this);
        
        renderer = new Renderer();
        rand = new Random();
        
        jframe.add(renderer);
        jframe.setTitle("Runner");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.addMouseListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);
        
        man = new Rectangle(WIDTH/2, HEIGHT/2, 20, 20);
        
        columns = new ArrayList<Rectangle>();
        
        jumping = 0;
        
        addColumn(true);
        addColumn(true);
        addColumn(true);
        addColumn(true);
        
        timer.start();
    }

    public void addColumn(boolean start) {
        //int space = 300;
        int width = 10;
        int height = 10 + rand.nextInt(80);
        
        
        if (start)
        {
            columns.add(new Rectangle(WIDTH + width + columns.size() * 900, HEIGHT - height - 120, width, height));
            //columns.add(new Rectangle(WIDTH + width + (columns.size() - 1) * 300, 0, width, HEIGHT - height - space));
        }
        else
        {
            columns.add(new Rectangle(columns.get(columns.size()-1).x + 600, HEIGHT - height - 120, width, height));
            //columns.add(new Rectangle(columns.get(columns.size() - 1).x, 0, width+100, HEIGHT - height - space));
        }
        
    }
    
    public void paintColumn(Graphics g, Rectangle column) {
        g.setColor(Color.green.darker());
        g.fillRect(column.x, column.y, column.width, column.height);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        int speed = 10;
        
        ticks++;
        
        if (started) {
        
            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);
                column.x -= speed;
            }

            if (ticks % 2 == 0 && ymotion < 15){
                ymotion += 2;
            }

            for (int i = 0; i < columns.size(); i++) {
                Rectangle column = columns.get(i);

                if (column.x + column.width < 0) {
                    columns.remove(column);
                    addColumn(false);

                    if(column.y == 0) {
                        addColumn(false);
                    }
                }
            }

            man.y += ymotion;

            for (Rectangle column : columns) {
                if (column.intersects(man)) {
                    gameOver = true;
                    
                    man.x = column.x - man.width;
                }
            }
            
            //réinitialise le double saut quand 
            if (man.y == 460) {
                jumping = 0;
            }
            if (man.y >= HEIGHT - 140 || man.y < 0) {
                man.y = HEIGHT-140;
            }
            if (gameOver) {
                man.y = HEIGHT - 120 - man.height;
            }
        }
        
        renderer.repaint();
    } 

    public void jump() {
        if (gameOver) {
            man = new Rectangle(WIDTH/2, HEIGHT/2, 20, 20);
            columns.clear();
            ymotion = 0;
            score = 0;
            
            gameOver = false;
        }
        if (!started) {
            started = true;
        } else if (!gameOver) {
            if (ymotion > 0) {
                ymotion = 0;
            }
            if(jumping < 2) {
                jumping++;
                ymotion -= 10;
            }
            System.out.println(jumping);
            System.out.println(man.y);
            
        }
    }
    
    void repaint(Graphics g) {
        g.setColor(Color.cyan);
        g.fillRect(0,0,WIDTH,HEIGHT);
        
        g.setColor(Color.orange);
        g.fillRect(0, HEIGHT-120, WIDTH, HEIGHT-120);
        
        g.setColor(Color.green);
        g.fillRect(0, HEIGHT-120, WIDTH, 20);
        
        g.setColor(Color.red);
        g.fillRect(man.x, man.y, man.width, man.height);
        
        for (Rectangle column : columns) {
            paintColumn(g, column);
        }
        
        g.setColor(Color.white);
        g.setFont(new Font("Arial", 1, 100));
        
        if (gameOver) {
            g.drawString("GameOver", 75, HEIGHT/2);
        }
        if (!started) {
            g.drawString("Click to start", 75, HEIGHT/2);
        }
    }
    
    public static void main(String[] args) {
        runner = new Game(args);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        jump();
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
