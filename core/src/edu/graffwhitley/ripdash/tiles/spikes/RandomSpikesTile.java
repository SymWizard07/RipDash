package edu.graffwhitley.ripdash.tiles.spikes;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class RandomSpikesTile extends StaticTile {

    public static final int RANDOM_SPIKES_0 = SpritePool.addSprite("./Collision/RandomSpikes.png", 23.0f, 12.5f);
    public static final int RANDOM_SPIKES_1 = SpritePool.addSprite("./Collision/RandomSpikes1.png", 23.0f, 12.5f);
    public static final int RANDOM_SPIKES_2 = SpritePool.addSprite("./Collision/RandomSpikes2.png", 23.0f, 12.5f);
    public static final int RANDOM_SPIKES_3 = SpritePool.addSprite("./Collision/RandomSpikes3.png", 23.0f, 12.5f);

    public RandomSpikesTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        
        bodyShape.setAsBox(1.0f, 0.4f);
        bodyDef.position.y -= 0.6;

        contactType = ContactType.SPIKE;
    }
    
}
