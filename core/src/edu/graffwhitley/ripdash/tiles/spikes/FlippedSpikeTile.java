package edu.graffwhitley.ripdash.tiles.spikes;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class FlippedSpikeTile extends StaticTile {

    public static final int FLIPPEDSPIKE = SpritePool.addSprite("./Collision/FlippedSpike.png", 23.0f, 12.5f);

    public FlippedSpikeTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        //this is for the flipped half spike
        Vector2 vertex1 = new Vector2(-2 / 2, 2 / 2);
        Vector2 vertex2 = new Vector2(2 / 2, 2 / 2);
        Vector2 vertex3 = new Vector2(0, -2 / 2);

        bodyShape = new PolygonShape();
        bodyShape.set(new Vector2[] {vertex1, vertex2, vertex3});
        
        contactType = ContactType.SPIKE;
    }
    
}
