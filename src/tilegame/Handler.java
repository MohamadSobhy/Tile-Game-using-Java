
package tilegame;

import tilegame.gfx.GameCamera;
import tilegame.input.KeyManager;
import tilegame.worlds.World;


public class Handler {
    
    private Game game;
    private World world;
    
    public Handler(Game game)
    {
        this.game = game;
    }
    
    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }
    public int getWidth()
    {
        return game.getWidth();
    }
    public int getHeight()
    {
        return game.getHeight();
    }

    public GameCamera getGameCamera()
    {
        return game.getGameCamera();
    }
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
    
}
