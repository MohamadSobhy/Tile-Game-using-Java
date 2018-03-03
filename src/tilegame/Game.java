package tilegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import tilegame.display.Display;
import tilegame.gfx.Assets;
import tilegame.gfx.GameCamera;
import tilegame.gfx.ImageLoader;
import tilegame.gfx.SpriteSheet;
import tilegame.input.KeyManager;
import tilegame.states.GameState;
import tilegame.states.MenuState;
import tilegame.states.State;

public class Game implements Runnable{
    
    private Display display;
    private Thread thread;
    private int width ,height;
    public String title;
    private boolean running = false;
    private BufferStrategy bs;
    private Graphics g;
    
    //States 
    private State gameState;
    private State menuState;
    
    //Input
    private KeyManager keyManager;
    
    //Camera 
    GameCamera gameCamera;
    
    //Handler
    private Handler handler;
    public Game(String title , int width , int height)
    {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
    }
    
    private void initComponents()
    {
        display = new Display(title , width , height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();
        
        handler = new Handler(this);
        gameCamera = new GameCamera( handler , 0 , 0 );
        
        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        State.setCurrentState(gameState);
    }
    
    private void tick()
    {
        keyManager.tick();
        if(State.getCurrentState() != null)
            State.getCurrentState().tick();
    }
    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        
        //Clear Screen 
        g.clearRect(0,0, width, height);
        //Draw 
        
        if(State.getCurrentState() != null)
            State.getCurrentState().render(g);
        
        
        
        //End Draw 
        bs.show();
        g.dispose();
    }
    public void run()
    {
        initComponents();
        int fps = 60;  //frame per second 
        double timePerTick = 1000000000/fps;
        double delta = 0;
        long now ;
        long lastTime = System.nanoTime();
        int timer = 0;
        int ticks = 0;
        while(running)
        {
            now = System.nanoTime();
            delta += (now-lastTime)/timePerTick;
            timer += now - lastTime;
            lastTime = now;
            
            if(delta >= 1){
            tick();
            render();
            delta--;
            ticks++;
            }
            
            if(timer >= 1000000000)
            {
                System.out.println("Ticks and Frames : " + ticks );
                ticks = 0;
                timer = 0;
            }
            
        }
        stop();
    }
    
    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public GameCamera getGameCamera() {
        return gameCamera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public synchronized void start()
    {
        if(running)
            return;
        
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    
    public synchronized void stop()
    {
        if(!running)
            return;
        
        running = false;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, ex,"ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
}
