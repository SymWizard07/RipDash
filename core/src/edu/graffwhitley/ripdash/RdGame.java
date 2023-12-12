package edu.graffwhitley.ripdash;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import edu.graffwhitley.ripdash.character.CharacterType;
import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.music.MusicPlayer;
import edu.graffwhitley.ripdash.tiles.StaticTile;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.*;

public class RdGame extends ApplicationAdapter {

	public static final boolean DEBUG_MODE = true;

	SpriteBatch batch;
	Texture img;
	World world;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	BitmapFont debugFont;

	String levelPath = "./Levels/tiles (18).json";
	Level activeLevel;

	int bgSpriteIndex;
	Sprite bgSprite;
	float bgSpriteWidth = 61.64f;
	float bgXPos = 0.0f;
	float bgXPos2 = bgSpriteWidth;
	Vector2 gravity = new Vector2(0, -160);

	CharacterType character;

	ArrayList<StaticTile> staticTiles = new ArrayList<>();

	public void restartLevel() {

		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);

		for (Body body : bodies) {
			world.destroyBody(body);
		}

		camera.position.set(0, -25.0f, 0);
		activeLevel = LevelLoader.readLevel(levelPath, world);
		character = activeLevel.getCharacter();
	}

	@Override
	public void create() {
		batch = new SpriteBatch();

		world = new World(gravity, true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 48, 27);
		camera.position.set(0, -25.0f, 0);

		batch.setProjectionMatrix(camera.combined);

		bgSpriteIndex = SpritePool.addSprite("./Details/Background.png");

		activeLevel = LevelLoader.readLevel(levelPath, world);

		character = activeLevel.getCharacter();

		// Debug Cam
		if (DEBUG_MODE) {
			debugRenderer = new Box2DDebugRenderer();
			debugFont = new BitmapFont();
		}

		MusicPlayer.playMusic("./Sounds/RipDashFinal.wav");

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

		if (!character.alive) {
			restartLevel();
		}

		if (DEBUG_MODE) {
			debugRenderer.render(world, camera.combined);
		}

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

	public static LevelObject bodyToLevelObject(Body body) {
		return (LevelObject)body.getUserData();
	}
}
