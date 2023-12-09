package edu.graffwhitley.ripdash;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ContactType;

public abstract class LevelObject {

    public Vector2 hSize;
    public ContactType contactType;

    public LevelObject(float hWidth, float hHeight) {
        hSize = new Vector2(hWidth, hHeight);
    }
    
    public abstract void createBody(World world);

    public abstract void draw(SpriteBatch batch, Camera camera);

}
