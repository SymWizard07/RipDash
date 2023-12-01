package edu.graffwhitley.ripdash.tiles.ground;

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class HalfGroundTile extends StaticTile {

    public static final int HALF_SQUARE = SpritePool.addSprite("./Collision/HalfBlock.png", 23.0f, 12.5f);

    public HalfGroundTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        bodyShape.setAsBox(1.0f, 0.5f);
    }
    
}