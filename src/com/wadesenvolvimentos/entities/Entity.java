package com.wadesenvolvimentos.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private BufferedImage sprite;

    public Entity (int x, int y, int width, int height, BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.sprite = sprite;
    }

    // getting and setting
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    // render abd tick 

    public void tick() {
        
    }

    public void render(Graphics g) {
        g.drawImage(sprite, this.getX(), this.getY(), null);
    }

}
