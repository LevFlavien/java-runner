/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.JFrame;

/**
 *
 * @author Poulet
 */
public class Runner {
    
    public static Runner runner;
    
    public static Game game;
    
    public static Menu menu;
    
    public static final int WIDTH = 1300, HEIGHT = 600;
    
    public static Renderer renderer;
    
    public static enum STATE {
        MENU,
        GAME,
    };
    public static STATE state = STATE.MENU;
    
    public Runner(Object object) {
        JFrame jframe = new JFrame();
        
        renderer = new Renderer();
        
        menu = new Menu();
        
        game = new Game();
        
        jframe.add(renderer);
        
        jframe.addMouseListener(new MouseInput());
        
        jframe.setTitle("Runner");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
    
    public static void start() {
        Thread t = new Thread(game);
        t.start();
    }
    
    public static void main(String[] args) {
        runner = new Runner(args);
    }
}
