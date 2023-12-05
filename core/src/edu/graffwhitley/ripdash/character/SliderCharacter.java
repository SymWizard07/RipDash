package edu.graffwhitley.ripdash.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;

import edu.graffwhitley.ContactType;
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
        }

        xProgress += 0.3;
        body.setTransform(xProgress, body.getPosition().y, body.getAngle());

        if (isContacting(ContactType.GROUND)) {
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
            body.setAngularVelocity(-6.0f);
        }
    }
}
