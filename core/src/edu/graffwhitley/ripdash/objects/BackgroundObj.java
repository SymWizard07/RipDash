package edu.graffwhitley.ripdash.objects;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ripdash.LevelObject;
import edu.graffwhitley.ripdash.graphics.AdjustedSprite;
import edu.graffwhitley.ripdash.graphics.SpritePool;

public class BackgroundObj extends LevelObject {

    protected Vector2 position;
    AdjustedSprite sprite;

    public BackgroundObj(int poolIndex, float x, float y, float hWidth, float hHeight) {
        super(hWidth, hHeight);

        position = new Vector2(x, y);
        
        sprite = SpritePool.getSprite(poolIndex);
    }

    @Override
    public void createBody(World world) {}

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        batch.draw(sprite, position.x + sprite.offset.x - camera.position.x, position.y + sprite.offset.y - camera.position.y, hSize.x * 2, hSize.y * 2);
    }
    
}
