
package tilegame.entities.statics;

import java.awt.Graphics;
import tilegame.Handler;
import tilegame.entities.Entity;

public abstract class StaticEntities extends Entity{

    public StaticEntities(Handler handler, float x, float y, int width, int height) {
        super(handler, x, y, width, height);
    }
    
}
