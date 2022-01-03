package com.wadesenvolvimentos.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheets {

    private BufferedImage spriteSheets;

    public SpriteSheets(String path) {   
        try {
            spriteSheets = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // getting sub images
    public BufferedImage getSprite(int x, int y, int width, int height) {
        return spriteSheets.getSubimage(x, y, width, height);
    }

}
