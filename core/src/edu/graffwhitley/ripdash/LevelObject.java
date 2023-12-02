package edu.graffwhitley.ripdash;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;

public interface LevelObject {
    
    public void createBody(World world);

    public void draw(SpriteBatch batch, Camera camera);

}
