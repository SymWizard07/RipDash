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

import edu.graffwhitley.ripdash.graphics.SpritePool;
import edu.graffwhitley.ripdash.tiles.StaticTile;
import edu.graffwhitley.ripdash.tiles.ground.GroundTile;
import edu.graffwhitley.ripdash.tiles.spikes.SpikeTile;

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
	float bgXPos2 = 61.64f;

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

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(0, 0);

		playerBody = world.createBody(bodyDef);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1, 1);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
		fixtureDef.density = 1.0f;
		playerBody.createFixture(fixtureDef);

		activeLevel = LevelLoader.readLevel("./Levels/TestLevel1.json", world);
		
		// Debug Cam
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.0f, 0.4f, 0.8f, 1);

		camera.update();
		debugRenderer.render(world, camera.combined);

		batch.begin();

		batch.setColor(0.0f, 0.4f, 0.8f, 1);
		batch.draw(SpritePool.getSprite(bgSpriteIndex), bgXPos, 0, 61.64f, 27.0f);
		batch.draw(SpritePool.getSprite(bgSpriteIndex), bgXPos2, 0, 61.64f, 27.0f);
		batch.setColor(Color.WHITE);
		
		activeLevel.drawObjects(batch, camera);

		batch.end();
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			playerBody.setLinearVelocity(0, 40.0f);
			playerBody.setAngularVelocity(-6.0f);
		}

		camera.position.add(0.3f, 0f, 0f);

		float parallaxSpeed = 0.05f;
		bgXPos -= parallaxSpeed;
		bgXPos2 -= parallaxSpeed;

		if (bgXPos + 61.64f < 0.0f) {
			bgXPos = 61.64f;
		}

		world.step(1 / 60f, 6, 2);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
