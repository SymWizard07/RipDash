package edu.graffwhitley.ripdash.character;

import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Fixture;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public class SliderCharacter extends CharacterType {

    public static int SLIDER = SpritePool.addSprite("./Collision/Player.png");
    private boolean onGround = false;

    public SliderCharacter(float x, float y) {
        super(SLIDER, x, y);
    }

    @Override
    protected void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE) && onGround) {
            body.setLinearVelocity(0, 35.0f);
        }

        xProgress += 0.3;
        body.setTransform(xProgress, body.getPosition().y, body.getAngle());

        for (Body contactBody : activeContacts) {
            if (body.getPosition().y < contactBody.getPosition().y) {
                alive = false;
                return;
            }
        }

        if (isContacting(ContactType.GROUND)) {
            onGround = true;

            float currentAngleDegrees = (body.getAngle() * MathUtils.radiansToDegrees) % 360;
            if (currentAngleDegrees < 0)
                currentAngleDegrees += 360;

            float nearest90Degree = Math.round(currentAngleDegrees / 90) * 90;
            float difference = nearest90Degree - currentAngleDegrees;

            float thresholdDegrees = 22.5f;

            if (Math.abs(difference) > thresholdDegrees && Math.abs(difference) < (90 - thresholdDegrees)) {
                float angularImpulse;

                if (difference > 0) {
                    angularImpulse = 20f;
                } else {
                    angularImpulse = -20f;
                }

                body.applyAngularImpulse(angularImpulse, true);
            }

        }
        if (!isContacting(ContactType.GROUND)) {
            onGround = false;
            body.setAngularVelocity(-6.0f);
        }
    }
}
