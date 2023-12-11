package edu.graffwhitley.ripdash.tiles.special;

import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class JumpPadTile extends StaticTile {

    public static final int JUMP_PAD = SpritePool.addSprite("./Collision/JumpPad.png", 23.0f, 12.5f);

    public JumpPadTile(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
        
        bodyShape.setAsBox(1.0f, 0.2f);
        bodyDef.position.y -= 0.8;

        contactType = ContactType.GROUND_BOOST;
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
