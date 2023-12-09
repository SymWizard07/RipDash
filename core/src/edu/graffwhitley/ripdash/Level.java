package edu.graffwhitley.ripdash;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import edu.graffwhitley.ripdash.character.CharacterType;

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

    public CharacterType getCharacter() {
        for (LevelObject levelObject : objects) {
            if (levelObject instanceof CharacterType) {
                return (CharacterType)levelObject;
            }
        }

        return null;
    }

    public void drawObjects(SpriteBatch batch, Camera camera) {
        for (LevelObject levelObject : objects) {
			levelObject.draw(batch, camera);
		}
    }

        

}
