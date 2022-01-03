package com.wadesenvolvimentos.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity{

	public boolean up, down, left, right;
	public int speed = 2;
	
    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }
    
    public void tick() {
    	if (right) {
    		x += speed;
    	} else if (left) {
    		x-=speed;
    	}
    	
    	if (up) {
    		y -= speed;
    	} else if (down) {
    		y += speed;
    	}
    }
    
}
