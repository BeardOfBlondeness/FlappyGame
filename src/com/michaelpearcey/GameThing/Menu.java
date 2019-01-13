package com.michaelpearcey.GameThing;

public class Menu {

    private Sprite bg;
    private Button play;
    private Button exit;
    private boolean menuUp;

    public Menu() {
        menuUp = true;
        bg = new Sprite(0, 0, 1200, 900, "bg.png");
        play = new Button(0, 300, 200, 50, "NewGame.png");
    }

    public void run() {
        if(menuUp) {
            bg.draw();
            play.run();
        }
        else
            FrameLoop.g.run();
        if(play.isMouseDown())
            menuUp = false;

    }
}
