package edu.graffwhitley.ripdash.objects.background;

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.objects.BackgroundObj;

public class LampPostBgObj extends BackgroundObj {

    public static final int LAMP_POST = SpritePool.addSprite("./Details/LampPost.png", 23.0f, 12.5f);

    public LampPostBgObj(float x, float y) {
        super(LampPostBgObj.LAMP_POST, x, y, 1.0f, 5.0f);
    }
    
}
