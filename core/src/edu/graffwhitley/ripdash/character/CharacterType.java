package edu.graffwhitley.ripdash.character;

import java.util.ArrayList;
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
import edu.graffwhitley.ripdash.RdGame;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.objects.CharacterTransformer;

public abstract class CharacterType extends LevelObject {

    protected PolygonShape bodyShape;
    protected BodyDef bodyDef;
    public Body body;
    protected Sprite sprite;
    public float xProgress;
    protected ArrayList<Body> activeContacts;
    public boolean alive;
    private CharacterType characterTypeRef = this;

    public CharacterType(int poolIndex, float x, float y) {
        super(0.9f, 0.9f);
        bodyShape = new PolygonShape();
        bodyShape.setAsBox(hSize.x, hSize.y);
        bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        contactType = ContactType.PLAYER;
        activeContacts = new ArrayList<>();

        this.sprite = new Sprite(SpritePool.getSprite(poolIndex));

        xProgress = 0.0f;
        alive = true;
    }

    public void createBody(World world) {
        body = world.createBody(bodyDef);
        body.createFixture(bodyShape, 1.0f);
        body.setUserData(this);

        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Fixture checkFixture;

                if (((LevelObject)fixtureA.getBody().getUserData()).contactType == ContactType.PLAYER) {
                    checkFixture = fixtureB;
                } else {
                    checkFixture = fixtureA;
                }

                if (((LevelObject)checkFixture.getBody().getUserData()).contactType == null) {
                    return;
                }

                LevelObject checkObject = RdGame.bodyToLevelObject(checkFixture.getBody());
                if (checkObject.contactType == ContactType.TRANSFORM) {
                    ((CharacterTransformer)checkObject).changeCharacter(characterTypeRef);
                }

                activeContacts.add(checkFixture.getBody());
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                Fixture checkFixture;

                if ((((LevelObject)(fixtureA.getBody().getUserData())).contactType) == ContactType.PLAYER) {
                    checkFixture = fixtureB;
                } else {
                    checkFixture = fixtureA;
                }

                if (((LevelObject)(checkFixture.getBody().getUserData())).contactType == null) {
                    return;
                }

                activeContacts.remove(checkFixture.getBody());
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

    public void removeBody() {
        RdGame.bodiesToDestroy.add(body);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    protected boolean isContacting(ContactType contactType) {
        for (Body body : activeContacts) {
            if (((LevelObject)(body.getUserData())).contactType == contactType) {
                return true;
            }
        }
        return false;
    }

    public void kill() {
        alive = false;
    }

    public void draw(SpriteBatch batch, Camera camera) {
        if (!alive) {
            return;
        }
        if (RdGame.started) {
            update();
        }

        sprite.setSize(hSize.x * 2, hSize.y * 2);
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