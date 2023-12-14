package edu.graffwhitley.ripdash;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.music.MusicPlayer;
import edu.graffwhitley.ripdash.tiles.StaticTile;
import com.badlogic.gdx.math.Vector2;

import com.badlogic.gdx.physics.box2d.*;

public class RdGame extends ApplicationAdapter {

	public static final boolean DEBUG_MODE = false;

	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
	Box2DDebugRenderer debugRenderer;
	BitmapFont debugFont; 

	String levelPath = "./Levels/OfficialLevel.json";
	public static World world;
	public static Level activeLevel;
	public static Queue<Body> bodiesToDestroy = new LinkedList<>();
	public static Queue<LevelObject> bodiesToCreate = new LinkedList<>();
	public static boolean win = false;
	public static boolean started = false;

	int bgSpriteIndex;
	Sprite bgSprite;
	float bgSpriteWidth = 61.64f;
	float bgXPos = 0.0f;
	float bgXPos2 = bgSpriteWidth;
	Vector2 gravity = new Vector2(0, -160);

	BitmapFont font;

	ArrayList<StaticTile> staticTiles = new ArrayList<>();

	public void restartLevel() {

		Array<Body> bodies = new Array<Body>();
		world.getBodies(bodies);

		for (Body body : bodies) {
			world.destroyBody(body);
		}

		started = false;

		camera.position.set(0, -25.0f, 0);
		activeLevel = LevelLoader.readLevel(levelPath, world);

		MusicPlayer.stopMusic();
		MusicPlayer.playMusic("./Sounds/RipDashFinal.wav");
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

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("./Fonts/OXYGENE1.TTF"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 120;
		font = generator.generateFont(parameter);
		generator.dispose();

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

		if (activeLevel.getCharacter() != null && !activeLevel.getCharacter().alive) {
			restartLevel();
		}

		if (DEBUG_MODE) {
			debugRenderer.render(world, camera.combined);
		}

		if (started) {
			camera.position.add(0.3f, 0f, 0f);
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			started = true;
		}

		float parallaxSpeed = 0.05f;
		bgXPos -= parallaxSpeed;
		bgXPos2 -= parallaxSpeed;

		if (bgXPos + bgSpriteWidth < 0.0f) {
			bgXPos = bgSpriteWidth;
		}
		if (bgXPos2 + bgSpriteWidth < 0.0f) {
			bgXPos2 = bgSpriteWidth;
		}

		if (started || !win) {
			world.step(1 / 60f, 6, 2);
		}

		if (camera.position.x >= activeLevel.winX) {
			win = true;
		}

		while (!bodiesToDestroy.isEmpty()) {
			Body body = bodiesToDestroy.poll();
			world.destroyBody(body);
		}

		while (!bodiesToCreate.isEmpty()) {
			LevelObject levelObject = bodiesToCreate.poll();
			levelObject.createBody(world);
		}
	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	public static LevelObject bodyToLevelObject(Body body) {
		return (LevelObject)body.getUserData();
	}
}
