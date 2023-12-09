package edu.graffwhitley.ripdash.tiles;

import com.badlogic.gdx.graphics.Camera;
//import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public abstract class StaticTile extends LevelObject {

	protected PolygonShape bodyShape;
	protected BodyDef bodyDef;
	protected Body body;
	protected AdjustedSprite sprite;

	public StaticTile(int poolIndex, float x, float y) {
		super(1, 1);
		bodyShape = new PolygonShape();
		bodyShape.setAsBox(hSize.x, hSize.y);
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.StaticBody;

		contactType = null;

		this.sprite = new AdjustedSprite(SpritePool.getSprite(poolIndex));
	}

	public void createBody(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);
		body.setUserData(this);
	}

	public void draw(SpriteBatch batch, Camera camera) {
		batch.draw(sprite, body.getPosition().x + sprite.offset.x - camera.position.x, body.getPosition().y + sprite.offset.y - camera.position.y, hSize.x * 2, hSize.y * 2);
	}
}
