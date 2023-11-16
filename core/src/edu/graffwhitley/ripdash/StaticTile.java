package edu.graffwhitley.ripdash;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public abstract class StaticTile {
    
    protected Body body;
    protected Sprite sprite;

    StaticTile(World world, float x, float y) {
        BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, -10)); // Set the position of the ground in the world
		groundBodyDef.type = BodyDef.BodyType.StaticBody;

		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(20 / 2, 1 / 2); // Set the size of the ground box

		groundBody.createFixture(groundBox, 0.0f); // 0.0f is the density, which is irrelevant for static bodies

		groundBox.dispose();
    }

}
