package com.michaelpearcey.GameThing;

import java.util.Random;

public class Column extends Sprite  {

    private Sprite column2;
    private final int spaceDifference = 70;
    private int i;
    private int endY1;
    private int startY2;

    public Column(int i) {
        super(1200 + (i*500), 0, 50, 0, "column.png");
        this.i = i;
        Random ran = new Random();
        int spaceLocationRan = ran.nextInt(200) + 350;
        endY1 = spaceLocationRan - spaceDifference;
        startY2 = spaceLocationRan + spaceDifference;
        this.setyRes(endY1);
        column2 = new Sprite(1200+(i*500), startY2, 50, 900, "column.png");
    }

    public void run() {
        this.draw();
        column2.draw();
        setxPos(getxPos() - 5);
        column2.setxPos(column2.getxPos() - 5);
        if(getxPos() < 0)
            reset();
        checkCollision();
        if(getxPos() == 600)
            FrameLoop.g.incrementScore();
    }

    public void reset() {
        Random ran = new Random();
        int spaceLocationRan = ran.nextInt(200) + 350;
        endY1 = spaceLocationRan - spaceDifference;
        startY2 = spaceLocationRan + spaceDifference;
        this.setyRes(endY1);
        column2.setyPos(startY2);
        this.setxPos(1200+(300));
        column2.setxPos(1200+(300));
    }

    public void checkCollision() {
        Character c = FrameLoop.g.getC();
        if(c.getxPos() > xPos && c.getxPos() < xPos+xRes && (c.getyPos()+5 < endY1 || c.getyPos()+c.getyRes()-5 > startY2))
            FrameLoop.g.lose();
    }
}
