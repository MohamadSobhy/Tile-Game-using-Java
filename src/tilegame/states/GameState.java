
package tilegame.states;

import java.awt.Graphics;
import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.creature.Player;
import tilegame.entities.statics.Tree;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;
import tilegame.worlds.World;

public class GameState extends State{
    
    private World world;
    public GameState(Handler handler)
    {
        super(handler);
        world = new World(handler , "src/res/worlds/world1.txt");
        handler.setWorld(world);
        
    }

    @Override
    public void tick() {
        world.tick();
        
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
    }
    
    
}
