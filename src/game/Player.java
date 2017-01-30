package game;

import java.awt.*;

/**
 * @author flevesque
 */
class Player extends Rectangle {

    public int ymotion;
    public int jumping = 0; // etat du saut 0: au sol, 1: premier saut, 2: double saut
    public boolean crouched = false; // false: player debout, true: player accroupi

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    // fait sauter player
    public void jump() {
        if (ymotion > 0) {
            ymotion = 0;
        }
        if (jumping < 2) {
            jumping++;
            ymotion -= 10;
        }
    }

    // fait se baisser player
    public void crouch() {
        this.height -= 20;
        this.y += 20;
        System.out.println("CROUCHED : " + height);
    }

}
