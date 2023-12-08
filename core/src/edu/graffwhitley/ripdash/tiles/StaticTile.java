package edu.graffwhitley.ripdash.tiles;

import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public abstract class StaticTile implements LevelObject {

	protected PolygonShape bodyShape;
	protected BodyDef bodyDef;
	protected Body body;
	protected AdjustedSprite sprite;
	protected ContactType contactType;

	public StaticTile(int poolIndex, float x, float y) {
		bodyShape = new PolygonShape();
		bodyShape.setAsBox(1, 1);
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.StaticBody;

		contactType = null;

		this.sprite = new AdjustedSprite(SpritePool.getSprite(poolIndex));
	}

	public void createBody(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);
		body.setUserData(contactType);
	}

	public void draw(SpriteBatch batch, Camera camera) {
		batch.draw(sprite, body.getPosition().x + sprite.offset.x - camera.position.x, body.getPosition().y + sprite.offset.y - camera.position.y, 2, 2);
	}
}
