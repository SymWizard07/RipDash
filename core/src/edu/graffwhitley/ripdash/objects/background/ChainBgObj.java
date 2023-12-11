package edu.graffwhitley.ripdash.objects.background;

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.objects.BackgroundObj;

public class ChainBgObj extends BackgroundObj {

    public static final int CHAIN = SpritePool.addSprite("./Details/Chain.png", 23.0f, 12.5f);

    public ChainBgObj(float x, float y) {
        super(ChainBgObj.CHAIN, x, y, 1.0f, 4.17f);
    }
}
