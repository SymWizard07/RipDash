package edu.graffwhitley.ripdash.tiles.special;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;

public class CoinPickup extends StaticTile {

    public static final int[] COIN_PICKUP = {
        SpritePool.addSprite("./Collision/Coin.png", 23.0f, 12.5f),
        SpritePool.addSprite("./Collision/Coin1.png", 23.0f, 12.5f),
        SpritePool.addSprite("./Collision/Coin2.png", 23.0f, 12.5f)
    };

    private int animationFramerate = 8;
    private int animationFrame = 0;
    private int animationIndex = 0;

    public CoinPickup(float x, float y) {
        super(CoinPickup.COIN_PICKUP[0], x, y);

        contactType = ContactType.COIN;

    }

    public void createBody(World world) {
		body = world.createBody(bodyDef);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);
		body.setUserData(this);
	}
    
    public void draw(SpriteBatch batch, Camera camera) {
        if (animationFrame >= animationFramerate) {
            animationIndex++;
            if (animationIndex >= COIN_PICKUP.length) {
                animationIndex = 0;
            }
            animationFrame = 0;
        }

        animationFrame++;

        sprite = SpritePool.getSprite(CoinPickup.COIN_PICKUP[animationIndex]);

		batch.draw(sprite, body.getPosition().x + sprite.offset.x - camera.position.x, body.getPosition().y + sprite.offset.y - camera.position.y, hSize.x * 2, hSize.y * 2);
	}
}
