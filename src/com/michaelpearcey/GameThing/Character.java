package com.michaelpearcey.GameThing;

import org.lwjgl.input.Keyboard;

public class Character extends Sprite {

    private String texPath;
    private int x;
    private int y;
    private int h;
    private int w;
    private boolean jump = false;
    private double velocity = 0;
    private int sheetIndex = 0;
    private int sheetIndexChange = 0;
    private boolean up = true;

    public Character() {
        super(575, 425, 50, 50, "BirdSheet.png");
    }

    public void run() {
        drawSheet();
        jump();

        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)== true && jump == false) {
            velocity = 7;
            jump = true;
        } else if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)!= true){
            jump = false;
        }
    }

    private void jump() {
        setyPos(getyPos() - (int)velocity);
        if(velocity > -5)
            velocity-=.2;
    }

    private void drawSheet() {
        drawSelection(sheetIndex*50, 0, 50, 50, 50, 50);
        if(sheetIndexChange < 4) {
            sheetIndexChange++;
        } else {
            sheetIndexChange = 0;
            if(sheetIndex < 5 && up)
                sheetIndex++;
            else if(sheetIndex == 5)
                up = false;
            if(!up && sheetIndex > 0)
                sheetIndex--;
            else if(sheetIndex == 0)
                up = true;
        }
    }
}
