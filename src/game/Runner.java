
package game;

import javax.swing.JFrame;

/**
 *
 * Runner.java
 * Classe principale
 * Instancie la vue, le MouseListener, démarre le thread du jeu
 */
public class Runner {
    
    public static Runner runner;
    
    public static Game game;
    
    public static Menu menu;
    
    public static final int WIDTH = 1300, HEIGHT = 600;
    
    public static Thread t;
    
    public static Renderer renderer;
    
    public static enum STATE {
        MENU,
        HISCORE,
        GAME,
        OVER
    };
    public static STATE state = STATE.MENU;
    
    public Runner(Object object) {
        JFrame jframe = new JFrame();
        
        renderer = new Renderer();
        
        menu = new Menu();
        
        jframe.add(renderer);
        
        jframe.addMouseListener(new MouseInput());
        
        jframe.setTitle("Runner");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
    
    public static void start() {
        game = new Game(); // nouvelle instance de Game crée à chaque nouveau démarrage
        t = new Thread(game);
        t.start();
    }
    
    public static void quit() {
        System.exit(0);
    }
    
    public static void main(String[] args) {
        runner = new Runner(args);
    }
}
