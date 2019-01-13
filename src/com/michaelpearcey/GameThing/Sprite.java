package com.michaelpearcey.GameThing;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glColor4f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;

public class Sprite {

	int xPos;
	int yPos;
	float xRes;
	float yRes;
	static float RATIO = 1;
	private float alpha = 0;
	private boolean fadeIn = true;
	private static boolean fadeFinish = false;
	private Texture tex;

	public static void setFadeFinish(boolean a) {
		fadeFinish = a;
	}
	public static float getRatio() {
		return RATIO;
	}
	public void setRatio(float ratio) {
		RATIO = ratio;
	}
	public void setxRes(float xRes) {
		this.xRes = xRes;
	}
	public void setyRes(float yRes) {
		this.yRes = yRes;
	}
	public int getxPos() {
		return xPos;
	}
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	public int getyPos() {
		return yPos;
	}
	public void setyPos(int yPos) {
		this.yPos = yPos;
	}
	public float getxRes() {
		return xRes;
	}
	public void setxRes(int xRes) {
		this.xRes = xRes;
	}
	public float getyRes() {
		return yRes;
	}
	public void setyRes(int yRes) {
		this.yRes = yRes;
	}

	public Sprite(int xPos, int yPos, float xRes, float yRes, String texPath) {
		this.tex = loadTexture(texPath, "PNG");
		this.xPos = xPos;
		this.yPos = yPos;
		this.xRes = xRes;
		this.yRes = yRes;
	}

	public void draw() {
		glPushMatrix();
		tex.bind();
		glTranslatef(xPos, yPos, 1);
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);                 			 glVertex2f(0, 0);
			glTexCoord2f(0, tex.getHeight());   			 glVertex2f(0, yRes);
			glTexCoord2f(tex.getWidth(), tex.getHeight()); 	 glVertex2f(xRes,yRes);
			glTexCoord2f(tex.getWidth(), 0);				 glVertex2f(xRes,0);
		}
		glEnd();

		glPopMatrix();
	}

	public static Texture loadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream("res/" + path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return tex;
	}



	/*
	 * Don't judge me I'm lazy
	 */
	public void drawSelection(int posX, int posY, int sizeX, int sizeY, int drawSizeX, int drawSizeY) {
		
		float startX = ((float)posX/(float)tex.getImageWidth())*tex.getWidth();
		float startY = ((float)posY/(float)tex.getImageHeight())*tex.getHeight();
		float endX = (startX+(((float)sizeX/(float)tex.getImageWidth())*tex.getWidth()));
		float endY = (startY+((float)sizeY/(float)tex.getImageHeight())*tex.getHeight());//posY+(sizeY/tex.getHeight());
		glPushMatrix();
		tex.bind();
		glTranslatef(xPos, yPos, 1);
		glColor3f(1,1,1);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(startX, startY);                 	 glVertex2f(0, 0);
			glTexCoord2f(startX, endY);   					 glVertex2f(0, drawSizeY);
			glTexCoord2f(endX, endY); 						 glVertex2f(drawSizeX, drawSizeY);
			glTexCoord2f(endX, startY);						 glVertex2f(drawSizeX,0);
		}
		glEnd();

		glPopMatrix();
	}
	
	

	
	public void drawFade() {
		glPushMatrix();
		tex.bind();
		glTranslatef(xPos, yPos, 1);
		glColor4f(1,2,3,alpha);
		glBegin(GL_QUADS);
		{
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(0, tex.getHeight());
			glVertex2f(0, yRes);
			glTexCoord2f(tex.getWidth(), tex.getHeight());
			glVertex2f(xRes,yRes);
			glTexCoord2f(tex.getWidth(), 0);
			glVertex2f(xRes,0);
		}
		glEnd();
		glPopMatrix();
		doubleFadeAlpha();
	}

	public void doubleFadeAlpha() {
		if(!fadeFinish) { 
			if(alpha <= 1.2 && fadeIn) {
				alpha+=0.005;
			}else if(alpha >= -0.05){
				fadeIn = false;
				alpha-=0.005;
			} else {
				fadeFinish = true;
			}
		} 
	}

	public boolean checkFadeStop() { 
		return fadeFinish;
	}

}
