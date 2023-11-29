package edu.graffwhitley.ripdash.graphics;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpritePool {

    private static ArrayList<AdjustedSprite> pool = new ArrayList<>();

    private SpritePool(){};

    public static int addSprite(Sprite sprite) {
        SpritePool.pool.add(new AdjustedSprite(sprite, 0.0f, 0.0f));
        return SpritePool.pool.size() - 1;
    }

    public static int addSprite(String texturePath) {
        SpritePool.pool.add(new AdjustedSprite(new Sprite(new Texture(Gdx.files.internal(texturePath))), 0, 0));
        return SpritePool.pool.size() - 1;
    }

    public static int addSprite(Sprite sprite, float offsetX, float offsetY) {
        SpritePool.pool.add(new AdjustedSprite(sprite, offsetX, offsetY));
        return SpritePool.pool.size() - 1;
    }

    public static int addSprite(String texturePath, float offsetX, float offsetY) {
        SpritePool.pool.add(new AdjustedSprite(new Sprite(new Texture(Gdx.files.internal(texturePath))), offsetX, offsetY));
        return SpritePool.pool.size() - 1;
    }

    public static AdjustedSprite getSprite(Sprite sprite) {
        return SpritePool.pool.get(pool.indexOf(sprite));
    }

    public static AdjustedSprite getSprite(int spriteIndex) {
        return SpritePool.pool.get(spriteIndex);
    }
}
