package edu.graffwhitley.ripdash.tiles.ground;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class GroundTile extends StaticTile {

    public static final int SQUARE = SpritePool.addSprite("./Collision/Square.png", 23.0f, 12.5f);
    public static final int BRICK = SpritePool.addSprite("./Collision/BrickTile.png", 23.0f, 12.5f);

    public GroundTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        contactType = ContactType.GROUND;
    }

}
