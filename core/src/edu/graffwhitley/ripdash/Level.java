package edu.graffwhitley.ripdash;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {
    
    private ArrayList<LevelObject> objects;

    public Level() {
        objects = new ArrayList<>();
    }

    public void addObject(LevelObject object) {
        objects.add(object);
    }

    public void removeObject(LevelObject object) {
        objects.remove(object);
    }

    public void drawObjects(SpriteBatch batch, Camera camera) {
        for (LevelObject levelObject : objects) {
			levelObject.draw(batch, camera);
		}
    }

}
