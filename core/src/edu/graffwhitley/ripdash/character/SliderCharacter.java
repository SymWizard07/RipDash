package edu.graffwhitley.ripdash.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import edu.graffwhitley.ripdash.graphics.SpritePool;

public class SliderCharacter extends CharacterType {

    public static int SLIDER = SpritePool.addSprite("./Collision/Player.png");

    public SliderCharacter(float x, float y) {
        super(SLIDER, x, y);
    }

    @Override
    protected void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			body.setLinearVelocity(0, 40.0f);
			body.setAngularVelocity(-6.0f);
		}
        
        xProgress += 0.3;
        body.setTransform(xProgress, body.getPosition().y, body.getAngle());
    }
}
