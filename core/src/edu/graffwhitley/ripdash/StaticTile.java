package edu.graffwhitley.ripdash;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class StaticTile {
    
	static PolygonShape defaultShape;

	static {
		defaultShape = new PolygonShape();
		defaultShape.setAsBox(1, 1);
	}

	protected BodyDef bodyDef;
    protected Body body;
    protected Sprite sprite;

    StaticTile(float x, float y) {
        bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y)); // Set the position of the ground in the world
		bodyDef.type = BodyDef.BodyType.StaticBody;

		body = world.createBody(bodyDef);

		body.createFixture(StaticTile.tileShape, 0.0f);
    }

	void createTile(World world) {
		world.createBody(bodyDef);
	}

}
