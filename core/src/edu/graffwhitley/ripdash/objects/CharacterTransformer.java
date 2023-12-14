package edu.graffwhitley.ripdash.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.character.CharacterType;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.music.MusicPlayer;

public class CharacterTransformer extends LevelObject {

    public static final int SHIP_TRANSFORMER = SpritePool.addSprite("./Collision/Tunnel.png", 23.0f, 12.5f);

    protected PolygonShape bodyShape;
	protected BodyDef bodyDef;
	protected Body body;
	protected AdjustedSprite sprite;

    public <T extends CharacterType> CharacterTransformer(Class<T> characterType, float x, float y) {
        super(1.0f, 1.8f);

        bodyShape = new PolygonShape();
		bodyShape.setAsBox(hSize.x, hSize.y);
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.StaticBody;

        switch (characterType.getName()) {
            case "SliderCharacter":
                sprite = SpritePool.getSprite(CharacterTransformer.SHIP_TRANSFORMER);
                break;
            case "ShipCharacter":
                sprite = SpritePool.getSprite(CharacterTransformer.SHIP_TRANSFORMER);
                break;
            default:
                sprite = SpritePool.getSprite(CharacterTransformer.SHIP_TRANSFORMER);
        }

        contactType = ContactType.TRANSFORM;

    }

    @Override
    public void createBody(World world) {
        body = world.createBody(bodyDef);
		body.createFixture(bodyShape, 0.0f);
		body.setUserData(this);
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        batch.draw(sprite, body.getPosition().x + sprite.offset.x - camera.position.x, body.getPosition().y + sprite.offset.y - camera.position.y, hSize.x * 2, hSize.y * 2);
    }

    
    
}
