package edu.graffwhitley.ripdash.graphics;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class AdjustedSprite extends Sprite {
    
    public final Vector2 offset;

    public AdjustedSprite(Sprite sprite, float offsetX, float offsetY) {
        super(sprite);
        this.offset = new Vector2(offsetX, offsetY);
    }

    public AdjustedSprite(AdjustedSprite sprite) {
        super((Sprite)sprite);
        this.offset = sprite.offset.cpy();
    }

}
