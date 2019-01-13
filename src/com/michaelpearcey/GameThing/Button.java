package com.michaelpearcey.GameThing;

import org.lwjgl.input.Mouse;

public class Button extends Sprite {

	private boolean mouseOver;
	private boolean mouseDown;

	public Button(int x, int y, int xRes, int yRes, String path) {
		super(x, y, xRes, yRes, path);
		mouseOver = false;
		mouseDown = false;
	}

	public void run() {
		this.draw();
		checkMouseCoords();
	}

	public boolean isMouseDown() {
		if(mouseDown)
			return true;
		else
			return false;
	}
	private void checkMouseCoords() {
		if(Mouse.getX() > xPos && Mouse.getX() < xPos + xRes && 900 - Mouse.getY() > yPos && 900 - Mouse.getY() < yPos + yRes) {
			if(!mouseOver) {
				setxRes(getxRes() + 80);
				setyRes(getyRes() + 20);
				mouseOver = true;
			}
			checkMouseDown();
		} else if(mouseOver) {
			setxRes(getxRes() - 80);
			setyRes(getyRes() - 20);
			mouseOver = false;
		}
	}

	public void checkMouseDown() {
		if(Mouse.isButtonDown(0)) {
				mouseDown = true;
		}
	}
}