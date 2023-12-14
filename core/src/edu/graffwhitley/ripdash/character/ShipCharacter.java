package edu.graffwhitley.ripdash.character;

import org.lwjgl.Sys;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import edu.graffwhitley.ContactType;
import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.RdGame;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public class ShipCharacter extends CharacterType {

    public static int SHIP = SpritePool.addSprite("./Collision/Rocket.png");

    public ShipCharacter(float x, float y) {
        super(ShipCharacter.SHIP, x, y);

        RdGame.world.setGravity(new Vector2(0.0f, -65.0f));
    }

    @Override
    protected void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            body.applyForceToCenter(new Vector2(0.0f, 420.0f), true);
        }

        for (Body contactBody : activeContacts) {
            LevelObject contactObject = RdGame.bodyToLevelObject(contactBody);
            if (contactObject.contactType == ContactType.GROUND && body.getPosition().y < contactBody.getPosition().y) {
                alive = false;
                return;
            }
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
    }
    
}
