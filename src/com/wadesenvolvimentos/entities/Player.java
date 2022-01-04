package com.wadesenvolvimentos.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.wadesenvolvimentos.main.Game;

public class Player extends Entity{

	// variables
	public boolean up, down, left, right;
	public double speed = 0.51;
	public int moveToRight = 0, move = moveToRight, moveToLeft = 1, moveToUp = 2, moveToDown = 3;
	private int frames = 0, maxFrames = 12, index = 0, maxIndex = 1;
	private BufferedImage[] rightPlayer, leftPlayer, upPlayer, downPlayer;
	private boolean moved = false;
	
    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        
		// animations spriteSheets
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
		upPlayer = new BufferedImage[2];
		downPlayer = new BufferedImage[2];
        
		// loadings the animations sprites
        for (int i = 0; i < 2; i++) {        	
        	rightPlayer[i] = Game.spriteSheets.getSprite(64 + (i * 16), 0, 16, 16);
        }

		for (int i = 0; i < 2; i++) {        	
        	leftPlayer[i] = Game.spriteSheets.getSprite(64 + (i * 16), 16, 16, 16);
        }

		for (int i = 0; i < 2; i++) {
			upPlayer[i] = Game.spriteSheets.getSprite(96 + (i * 16), 0, 16, 16);
		}

		for (int i = 0; i < 2; i++) {
			downPlayer[i] = Game.spriteSheets.getSprite(32 + (i * 16), 0, 16, 16);
		}
    }
    
    public void tick() {

		// checking move
    	if (right) {
			moved = true;
			move = moveToRight;
    		x += speed;
    	} else if (left) {
			moved = true;
			move = moveToLeft;
    		x-=speed;
    	}
    	
    	else if (up) {
			moved = true;
			move = moveToUp;
    		y -= speed;
    	} else if (down) {
			moved = true;
			move = moveToDown;
    		y += speed;
    	} else {
			moved = false;
		}

		// animations positions
		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index ++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}
    }
    
    public void render(Graphics g) {
		if (move == moveToRight) {
			g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
		} else if (move == moveToLeft) {
			g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
		} else if (move == moveToUp) {
			g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
		} else if (move == moveToDown) {
			g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
		}
    }
    
}
