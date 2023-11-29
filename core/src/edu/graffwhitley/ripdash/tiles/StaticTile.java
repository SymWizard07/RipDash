package edu.graffwhitley.ripdash.tiles;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ripdash.graphics.SpritePool;

public abstract class StaticTile {

	static PolygonShape defaultShape;

	static {
		defaultShape = new PolygonShape();
		defaultShape.setAsBox(1, 1);
	}

	protected BodyDef bodyDef;
	protected Body body;
	protected Sprite sprite;

	public StaticTile(int poolIndex, float x, float y) {
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.StaticBody;

		this.sprite = new Sprite(SpritePool.getSprite(poolIndex));
	}

	public void createTile(World world) {
		body = world.createBody(bodyDef);
		body.createFixture(StaticTile.defaultShape, 0.0f);
	}

	public void draw(SpriteBatch batch) {
		batch.draw(sprite, body.getPosition().x + 23.0f, body.getPosition().y + 12.5f, 2, 2);
	}
}
