package edu.graffwhitley.ripdash.tiles.special;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class JumpBoostTile extends StaticTile {

    public static final int JUMP_BOOST = SpritePool.addSprite("./Collision/JumpBoost.png", 23.0f, 12.5f);

    public JumpBoostTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        
        contactType = ContactType.AIR_BOOST;
    }

    public void createBody(World world) {
		body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		body.setUserData(this);
	}
    
}
