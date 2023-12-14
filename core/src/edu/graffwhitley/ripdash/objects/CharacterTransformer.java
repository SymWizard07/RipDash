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
import edu.graffwhitley.ripdash.RdGame;
import edu.graffwhitley.ripdash.character.CharacterType;
import edu.graffwhitley.ripdash.character.ShipCharacter;
import edu.graffwhitley.ripdash.character.SliderCharacter;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.music.MusicPlayer;

public class CharacterTransformer extends LevelObject {

    public static final int SHIP_TRANSFORMER = SpritePool.addSprite("./Collision/Tunnel.png", 22.0f, 10.0f);

    protected PolygonShape bodyShape;
	protected BodyDef bodyDef;
	protected Body body;
	protected AdjustedSprite sprite;

    private String characterTypeName;

    public <T extends CharacterType> CharacterTransformer(String characterTypeName, float x, float y) {
        super(2.0f, 3.6f);

        bodyShape = new PolygonShape();
		bodyShape.setAsBox(hSize.x, hSize.y);
		bodyDef = new BodyDef();
		bodyDef.position.set(new Vector2(x, y));
		bodyDef.type = BodyDef.BodyType.StaticBody;

        this.characterTypeName = characterTypeName;
        switch (characterTypeName) {
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

    public void changeCharacter(CharacterType oldCharacter) {
        CharacterType newCharacter = null;

        switch (characterTypeName) {
            case "SliderCharacter":
                newCharacter = new SliderCharacter(oldCharacter.getPosition().x, oldCharacter.getPosition().y);
                break;
            case "ShipCharacter":
                newCharacter = new ShipCharacter(oldCharacter.getPosition().x, oldCharacter.getPosition().y);
                break;
        }

        newCharacter.xProgress = oldCharacter.xProgress;
        
        RdGame.activeLevel.setCharacter(newCharacter);
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
