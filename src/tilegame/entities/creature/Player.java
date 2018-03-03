
package tilegame.entities.creature;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import tilegame.Game;
import tilegame.Handler;
import tilegame.gfx.Animations;
import tilegame.gfx.Assets;

public class Player extends creature{

    Animations animDown , animUp , animRight , animLeft ;
    public Player(Handler handler , float x, float y) {
        super(handler , x , y , (int) creature.DEFAULT_CREATURE_WIDTH , (int) creature.DEFAULT_CREATURE_HEIGHT);
        
        bounds.x = 19;
        bounds.y = 27;
        bounds.width = 24;
        bounds.height = 32;
        animDown = new Animations(500, Assets.player_down);
        animUp = new Animations(500, Assets.player_up);
        animRight = new Animations(500, Assets.player_right);
        animLeft = new Animations(500, Assets.player_left);
    }

    @Override
    public void tick() {
        //Animations 
        animDown.tick();
        animUp.tick();
        animRight.tick();
        animLeft.tick();
        
        //Movement
        getInput();
        move();
        handler.getGameCamera().centerOnEntity(this);
    }
    public void getInput()
    {
        xMove = 0;
        yMove = 0;
        
        if(handler.getKeyManager().up)
            yMove = -speed;
        if(handler.getKeyManager().down)
            yMove = speed;
        if(handler.getKeyManager().left)
            xMove = -speed;
        if(handler.getKeyManager().right)
            xMove = speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage( getCurrentAnimationFrame(), (int) ( x - handler.getGameCamera().getxOffset() ) 
                ,(int) ( y - handler.getGameCamera().getyOffset() ) , width , height , null);
    }
    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove > 0)
            return animRight.getCurrentFrame();
        else if(xMove < 0)
            return animLeft.getCurrentFrame();
        else if(yMove < 0)
            return animUp.getCurrentFrame();
        else 
            return animDown.getCurrentFrame();
    }
}
