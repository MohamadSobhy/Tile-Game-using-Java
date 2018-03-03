
package tilegame.tiles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
    
    public static Tile [] tiles = new Tile[256];
    public static Tile grassTile = new GrassTile(0);
    public static Tile dirtTile = new DirtTile(1);
    public static Tile rockTile = new RockTile(2);
    
    
    public static final int TILEWIDTH = 64 , TILEHEIGHT = 64;
    
    protected BufferedImage image;
    protected final int id;
    
    public Tile(BufferedImage image , int id )
    {
        this.id = id;
        this.image = image;
        tiles[id] = this;
    }
    public void tick()
    {
        
    }
    public void render(Graphics g , int x , int y)
    {
        g.drawImage(image,x,y,TILEWIDTH ,TILEHEIGHT ,null);
    }
    public boolean isSolid()
    {
        return false;
    }
    public int getId()
    {
        return this.id;
    }
    
}
