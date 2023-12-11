package edu.graffwhitley.ripdash.tiles.ground;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class HalfGroundTile extends StaticTile {

    public static final int HALF_SQUARE = SpritePool.addSprite("./Collision/HalfBlock.png", 23.0f, 12f);
    public static final int HALF_SQUARE_BOTTOM = SpritePool.addSprite("./Collision/HalfBlock1.png", 23.0f, 12f);

    public HalfGroundTile(int poolIndex, float x, float y, boolean flipped) {
        super(poolIndex, x, y);
        bodyShape.setAsBox(1.0f, 0.5f);
        if (!flipped) {
            bodyDef.position.y += 0.5f;
        }
        else {
            bodyDef.position.y -= 0.5f;
        }
        contactType = ContactType.GROUND;
    }
    
}
