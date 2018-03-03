
package tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
    
    private static final int width = 100 , height = 100;
    public static BufferedImage dirt , grass , stone , tree ;
    public static BufferedImage player_down [];
    public static BufferedImage player_up [];
    public static BufferedImage player_right[];
    public static BufferedImage player_left [];
    
    public static void init()
    {
        SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/res/Photos/sheet.png"));
        
        player_down  = new BufferedImage[2];
        player_up    = new BufferedImage[2];
        player_right = new BufferedImage[2];
        player_left  = new BufferedImage[2];
    
    
        player_down[0] = sheet.crop( 4 * width ,   0   , width, height);
        player_down[1] = sheet.crop( 5 * width ,   0   , width, height);
        player_up[0] = sheet.crop(   6 * width ,   0   , width, height);
        player_up[1] = sheet.crop(   7 * width ,   0   , width, height);
        player_right[0] = sheet.crop(6 * width , width , width, height);
        player_right[1] = sheet.crop(7 * width , width , width, height);
        player_left[0] = sheet.crop( 4 * width , width , width, height);
        player_left[1] = sheet.crop( 5 * width , width , width, height);
        
        dirt = sheet.crop(width ,  0 , width , height - 1);
        grass = sheet.crop(2 * width + 1, 0 , width - 1, height - 1);
        stone = sheet.crop(3 * width - 1 , 0 , width , height - 1);
        tree = sheet.crop(0  , height + 1 , width - 1 ,  2 * height);
    }
    
}
