
package tilegame.worlds;

import java.awt.Graphics;
import tilegame.Game;
import tilegame.Handler;
import tilegame.entities.EntityManager;
import tilegame.entities.creature.Player;
import tilegame.entities.statics.Tree;
import tilegame.tiles.Tile;
import tilegame.utils.Utils;


public class World {
    
    private Handler handler;
    private int width , height;
    private int x , y;
    private int tiles[][];
    private EntityManager entityManager;
    
    public World(Handler handler , String path)
    {
        this.handler = handler;
        entityManager = new EntityManager(handler,new Player( handler, 100 , 100 ));
        entityManager.addEntity(new Tree( handler, 100 , 250 ));
        entityManager.addEntity(new Tree( handler, 100 , 350 ));
        entityManager.addEntity(new Tree( handler, 100 , 450 ));
        loadWorld(path);
        
        entityManager.getPlayer().setX(x);
        entityManager.getPlayer().setY(y);
    }
    public void tick()
    {
        entityManager.tick();
    }
    public void render(Graphics g)
    {
        int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
        int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth())/Tile.TILEWIDTH +1);
        int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
        int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
        
        
        for( int y = yStart ; y < yEnd ; y++){
            for(int x = xStart ; x < xEnd ; x++){
                getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset() ) 
                        , (int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset() ) );
            }
        }
        
        entityManager.render(g);
    }
    public Tile getTile(int x , int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.grassTile;
        
        
        Tile tile = Tile.tiles[tiles[x][y]];
        if(tile == null)
            return Tile.dirtTile;
        return tile;
    }
    public void loadWorld(String path)
    {
        String file = Utils.loadWorldAsString(path);
        String [] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        x = Utils.parseInt(tokens[2]);
        y = Utils.parseInt(tokens[3]);
        
        tiles = new int [width][height];
        
        for(int y = 0 ; y < height ; y++){
            for(int x = 0 ; x < width ; x++)
            {
                tiles [x][y] = Utils.parseInt(tokens[ (x + y * width)  + 4 ]);
            }
        }
        
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    
    
}
