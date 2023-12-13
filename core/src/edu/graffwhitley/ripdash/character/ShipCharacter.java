package edu.graffwhitley.ripdash.character;

import com.badlogic.gdx.math.MathUtils;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public class ShipCharacter extends CharacterType {

    public static int SHIP = SpritePool.addSprite("./Collision/Rocket.png");

    public ShipCharacter(int poolIndex, float x, float y) {
        super(poolIndex, x, y);
    }

    @Override
    protected void update() {
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
    }
    
}
