package edu.graffwhitley.ripdash;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import edu.graffwhitley.ripdash.character.SliderCharacter;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.Music.MusicPlayer;
import edu.graffwhitley.ripdash.tiles.StaticTile;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class RdGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	World world;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;

	Body playerBody;
	Body triBody;

	Level activeLevel;

	int bgSpriteIndex;
	Sprite bgSprite;
	float bgSpriteWidth = 61.64f;
	float bgXPos = 0.0f;
	float bgXPos2 = bgSpriteWidth;

	ArrayList<StaticTile> staticTiles = new ArrayList<>();

	@Override
	public void create() {
		batch = new SpriteBatch();

		world = new World(new Vector2(0, -160), true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 48, 27);
		camera.position.set(0, -25.0f, 0);

		batch.setProjectionMatrix(camera.combined);

		bgSpriteIndex = SpritePool.addSprite("./Details/Background.png");

		activeLevel = LevelLoader.readLevel("./Levels/level(4).json", world);
		
		// Debug Cam
		debugRenderer = new Box2DDebugRenderer();

		MusicPlayer.playMusic("./Sounds/RipDash.wav");

	}

	@Override
	public void render() {
		ScreenUtils.clear(0.0f, 0.4f, 0.8f, 1);

		camera.update();

		batch.begin();

		batch.setColor(0.0f, 0.4f, 0.8f, 1);
		batch.draw(SpritePool.getSprite(bgSpriteIndex), bgXPos, 0, 61.64f, 27.0f);
		batch.draw(SpritePool.getSprite(bgSpriteIndex), bgXPos2, 0, 61.64f, 27.0f);
		batch.setColor(Color.WHITE);

		activeLevel.drawObjects(batch, camera);

		batch.end();

		debugRenderer.render(world, camera.combined);

		camera.position.add(0.3f, 0f, 0f);

		float parallaxSpeed = 0.05f;
		bgXPos -= parallaxSpeed;
		bgXPos2 -= parallaxSpeed;

		if (bgXPos + bgSpriteWidth < 0.0f) {
			bgXPos = bgSpriteWidth;
		}
		if (bgXPos2 + bgSpriteWidth < 0.0f) {
			bgXPos2 = bgSpriteWidth;
		}

		world.step(1 / 60f, 6, 2);
	}

	@Override
	public void dispose() {
		batch.dispose();
	}
}
