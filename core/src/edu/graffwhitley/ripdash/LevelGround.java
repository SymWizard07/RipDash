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

import edu.graffwhitley.ContactType;

public class LevelGround extends LevelObject {

    private Body body;
    private Sprite sprite;
    private float yPos;

    public LevelGround(World world, float x) {
        super(24.0f, 8.12f);
        PolygonShape bodyShape = new PolygonShape();
		bodyShape.setAsBox(hSize.x, hSize.y);
		BodyDef bodyDef = new BodyDef();
        yPos = -40.2f;
		bodyDef.position.set(new Vector2(x, yPos));
		bodyDef.type = BodyDef.BodyType.StaticBody;

        body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);

        contactType = ContactType.GROUND;

        body.setUserData(this);

        sprite = new Sprite(new Texture(Gdx.files.internal("./Details/Ground.png")));
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        batch.setColor(Color.NAVY);
        batch.draw(sprite, body.getPosition().x + 23.0f - camera.position.x, body.getPosition().y + 12.5f - camera.position.y, 48f, 9.12f);
        batch.setColor(Color.WHITE);
        if (body.getPosition().x + 24f + 48f < camera.position.x){
            body.setTransform(body.getPosition().x + 48f * 2, yPos, body.getAngle());
        }
    }

    @Override
    public void createBody(World world) {}
    
}
