package edu.graffwhitley.ripdash.tiles.spikes;

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class SpikeTile extends StaticTile {

    public static final int SPIKE = SpritePool.addSprite("./Collision/Spike.png", 23.0f, 12.5f);

    public SpikeTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
    }
    
}
