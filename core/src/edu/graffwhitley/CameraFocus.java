package edu.graffwhitley;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import edu.graffwhitley.ripdash.LevelObject;

public class CameraFocus extends LevelObject {
    
    private Vector2 focusPoint;
    private float startY;
    private float alpha;

    public CameraFocus(float x, float y) {
        super(0f, 0f);
        focusPoint = new Vector2(x, y);
        alpha = 0;
    }

    public Vector2 getFocusPoint() {
        return focusPoint;
    }

    @Override
    public void createBody(World world) {
        
    }

    @Override
    public void draw(SpriteBatch batch, Camera camera) {
        if (alpha >= 1.0f) {
            return;
        }
        if (camera.position.x < focusPoint.x) {
            startY = camera.position.y;
        }
        else {
            camera.position.y = Interpolation.fastSlow.apply(startY, focusPoint.y, alpha);
            alpha += 0.05f;
        }
    }
}