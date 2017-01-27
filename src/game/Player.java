/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Rectangle;

/**
 *
 * @author flevesque
 */
public class Player extends Rectangle {

    public int ymotion;
    public int jumping = 0; // etat du saut 0: au sol, 1: premier saut, 2: double saut
    
    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        
    }
    
    public void jump() {
        if (Game.state != Game.STATE.GAME) {
            Game.state = Game.STATE.GAME;
        } else {
            if (ymotion > 0) {
                ymotion = 0;
            }
            if(jumping < 2) {
                jumping++;
                ymotion -= 10;
            }
            
        }
    }
    
}
