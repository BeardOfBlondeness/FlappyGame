package com.michaelpearcey.GameThing;

import org.newdawn.slick.TrueTypeFont;
import java.awt.*;
import org.newdawn.slick.Color;

public class Game {

    private Character c;
    private Column cl, cl2, cl3;
    private boolean lost = false;
    private int score = 0;
    private Sprite bg1, bg2;
    TrueTypeFont font;
    Font awtFont = new Font("Times New Roman", Font.BOLD, 24); //name, style (PLAIN, BOLD, or ITALIC), size

    public Game() {
        bg1 = new Sprite(0, 0, 1200, 900, "bg.png");
        bg2 = new Sprite(1200, 0, 1200, 900, "bg.png");
        font = new TrueTypeFont(awtFont, false); //base Font, anti-aliasing true/false
        c = new Character();
        cl = new Column(0);
        cl2 = new Column(1);
        cl3 = new Column(2);

    }

    public void run() {
        if(!lost){
            if(bg1.getxPos() > -1200)
                bg1.setxPos(bg1.getxPos() - 3);
            else
                bg1.setxPos(1195    );
            if(bg2.getxPos() > -1200)
                bg2.setxPos(bg2.getxPos() - 3);
            else
                bg2.setxPos(1195);
            bg1.draw();
            bg2.draw();
            c.run();
            cl.run();
            cl2.run();
            cl3.run();
            font.drawString(100, 50, Integer.toString(score), Color.yellow); //x, y, string to draw, color
        }
    }

    public void incrementScore() {
        score++;
    }

    public Character getC() {
        return c;
    }

    public void lose() {
        lost = true;
    }
}
