package edu.graffwhitley.ripdash;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelGround implements LevelObject {

    private Body body;

    public LevelGround(World world) {
        PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(1, 1);
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(0.0f, 0.0f));
		bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        
    }
    
}
