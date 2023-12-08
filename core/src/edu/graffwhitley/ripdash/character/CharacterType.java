package edu.graffwhitley.ripdash.character;

import java.util.HashMap;
import java.util.Map;
//import java.util.Set;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public abstract class CharacterType implements LevelObject {

    protected PolygonShape bodyShape;
    protected BodyDef bodyDef;
    protected Body body;
    protected Sprite sprite;
    protected float xProgress;
    protected Map<Fixture, ContactType> contactMap = new HashMap<>();

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
        body.setUserData(ContactType.PLAYER);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Fixture checkFixture;

                if ("player".equals(fixtureA.getBody().getUserData())) {
                    checkFixture = fixtureB;
                } else {
                    checkFixture = fixtureA;
                }

                if (checkFixture.getBody().getUserData() == null) {
                    return;
                }

                contactMap.put(checkFixture, (ContactType)checkFixture.getBody().getUserData());
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Fixture checkFixture;

                if ("player".equals(fixtureA.getBody().getUserData())) {
                    checkFixture = fixtureB;
                } else {
                    checkFixture = fixtureA;
                }

                if (checkFixture.getBody().getUserData() == null) {
                    return;
                }

                contactMap.remove(checkFixture);
            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
                // Handle collision before physics calculation
            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
                // Handle collision after physics calculation
            }
        });

    }

    protected boolean isContacting(ContactType contactType) {
        return contactMap.containsValue(contactType);
    }

    public void draw(SpriteBatch batch, Camera camera) {
        update();

        sprite.setSize(1.8f, 1.8f);
        sprite.setOriginCenter();
        sprite.setPosition(body.getPosition().x + 23.1f - camera.position.x,
                body.getPosition().y + 12.6f - camera.position.y);
        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        sprite.draw(batch);
        // batch.draw(sprite, body.getPosition().x + 23.0f - camera.position.x,
        // body.getPosition().y + 12.5f - camera.position.y, 1.8f, 1.8f);
    }

    protected abstract void update();
}