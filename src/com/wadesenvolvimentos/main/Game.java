package com.wadesenvolvimentos.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.wadesenvolvimentos.entities.Entity;
import com.wadesenvolvimentos.entities.Player;
import com.wadesenvolvimentos.graphics.SpriteSheets;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	// variables
    private Thread thread;
    private boolean isRunning = true;
    public static JFrame frame;
    private final int WIDTH = 440;
    private final int HEIGHT = 260;
    private final int SCALE = 2;
    private BufferedImage image;
    public List<Entity> entities;
    public SpriteSheets spriteSheets;
    
    private Player player;

    // imports

    // constructor method
    public Game(){
    	addKeyListener(this);
    	
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();

        // initializing object
        image = new BufferedImage(160, 120, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<Entity>();
        spriteSheets = new SpriteSheets("/spriteSheet.png");

        // Player
        player = new Player(0, 0, 16, 16, spriteSheets.getSprite(32, 0, 16, 16));
        entities.add(player);
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.start();
    }

    public void initFrame() {
        frame = new JFrame("Game #01"); // game title
        frame.add(this);
        frame.setResizable(false); // to not resize the window
        frame.pack(); //calcule dimensions  
        frame.setLocationRelativeTo(null); // to start window on center
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close java process on close window
        frame.setVisible(true);
    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // geme logics 
    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    // renders graphics 
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null ) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = image.getGraphics();
        g.setColor(new Color(19, 19, 19));
        g.fillRect(0, 0, WIDTH, HEIGHT);

        /* rendering game */
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render(g);
        }
        /* end rendering game */

        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
        bs.show();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        long lastTime = System.nanoTime(); //actual time in nano seconds
        double amountOfTicks = 60.0; // clocks quantities
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;

        // checking if is running at 60 frames por seconds
        int frames = 0;
        double timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;

            // checking if is running at 60 frames por seconds
            lastTime = now;

            if (delta >= 1) {
                tick();
                render();
                frames ++;
                delta--;
            }

            // checking if is running at 60 frames por seconds
            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }

        }

        stop();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			// right
			player.right = true;
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			// left
			player.left = true;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			// up
			player.up = true;
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			// down
			player.down = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// // TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			// right
			player.right = false;
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			// left
			player.left = false;
			
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			// up
			player.up = false;
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			// down
			player.down = false;
		}
		
	}

}
