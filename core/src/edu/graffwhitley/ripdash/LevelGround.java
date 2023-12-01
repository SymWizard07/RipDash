package edu.graffwhitley.ripdash;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class LevelGround implements LevelObject {

    private Body body;
    private Sprite sprite;

    public LevelGround(World world, float x) {
        PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(24f, 2.5f);
		BodyDef bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, -40f));
		bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);

        sprite = new Sprite(new Texture(Gdx.files.internal("./Details/Ground.png")));
        System.out.println(sprite);
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        batch.setColor(Color.NAVY);
        batch.draw(sprite, body.getPosition().x + 23.0f - camera.position.x, body.getPosition().y + 12.5f - camera.position.y, 48f, 9.12f);
        batch.setColor(Color.WHITE);
        if (body.getPosition().x + 24f + 48f < camera.position.x){
            body.setTransform(body.getPosition().x + 48f * 2, -40f, body.getAngle());
        }
    }
    
}
