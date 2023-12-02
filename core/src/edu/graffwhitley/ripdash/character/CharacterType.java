package edu.graffwhitley.ripdash.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public abstract class CharacterType implements LevelObject {
    
    protected PolygonShape bodyShape;
    protected BodyDef bodyDef;
    protected Body body;
    protected Sprite sprite;
    protected float xProgress;

    public CharacterType(int poolIndex, float x, float y) {
        bodyShape = new PolygonShape();
		bodyShape.setAsBox(0.9f, 0.9f);
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.DynamicBody;

		this.sprite = new Sprite(SpritePool.getSprite(poolIndex));

        xProgress = 0.0f;
    }

    public void createBody(World world) {
        body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 1.0f);
    }

    public void draw(SpriteBatch batch, Camera camera) {
        update();

        sprite.setSize(1.8f, 1.8f);
        sprite.setOriginCenter();
        sprite.setPosition(body.getPosition().x + 23.0f - camera.position.x, body.getPosition().y + 12.5f - camera.position.y);
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        //batch.draw(sprite, body.getPosition().x + 23.0f - camera.position.x, body.getPosition().y + 12.5f - camera.position.y, 1.8f, 1.8f);
    }

    protected abstract void update();
}