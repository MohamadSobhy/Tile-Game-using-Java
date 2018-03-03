
package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animations {
    
    private int speed , index;
    private BufferedImage frames [];
    private long timer , lastTime;
    
    public Animations(int speed , BufferedImage frames[])
    {
        this.speed = speed;
        this.frames = frames;
        this.index = 0;
        this.timer = 0;
        this.lastTime = System.currentTimeMillis();
    }
    
    public void tick()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        
        if(timer > speed)
        {
            index++;
            timer = 0;
            if(index >= frames.length)
                index = 0;
        }
    }
    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }
    
    
}
