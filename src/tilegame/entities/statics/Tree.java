/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tilegame.entities.statics;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.gfx.Assets;
import tilegame.tiles.Tile;

/**
 *
 * @author Mohamed Sobhy
 */
public class Tree extends StaticEntities{

    public Tree(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2 );
    }

    @Override
    public void tick() {
        
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.tree, (int) ( x - handler.getGameCamera().getxOffset() ), (int)  ( y - handler.getGameCamera().getyOffset() ), width , height , null);
    }
    
}
