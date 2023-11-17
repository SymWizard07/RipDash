package edu.graffwhitley.ripdash;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.math.MathUtils;
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

	@Override
	public void create() {
		batch = new SpriteBatch();

		world = new World(new Vector2(0, -160), true);

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 48, 27);
		camera.position.set(0, 0, 0);

		batch.setProjectionMatrix(camera.combined);

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

		// Ground
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.position.set(new Vector2(0, -10)); // Set the position of the ground in the world
		groundBodyDef.type = BodyDef.BodyType.StaticBody;

		Body groundBody = world.createBody(groundBodyDef);

		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(20 / 2, 1 / 2); // Set the size of the ground box

		groundBody.createFixture(groundBox, 0.0f); // 0.0f is the density, which is irrelevant for static bodies

		groundBox.dispose();

		// Spike

		PolygonShape triangleShape = new PolygonShape();
		triangleShape.set(new Vector2[] { new Vector2(-1, -1), new Vector2(1, -1), new Vector2(0, 1) });

		FixtureDef triDef = new FixtureDef();
		triDef.shape = triangleShape;
		triDef.density = 1.0f; // Adjust as needed
		// triDef.friction = 0.5f; // Adjust as needed
		// triDef.restitution = 0.3f; // Adjust as needed

		BodyDef triBodyDef = new BodyDef();
		triBodyDef.position.set(new Vector2(0, -2));
		triBodyDef.type = BodyDef.BodyType.DynamicBody; // Or StaticBody/KinematicBody as needed

		triBody = world.createBody(triBodyDef);
		triBody.createFixture(triDef);

		Texture triTexture = new Texture(Gdx.files.internal("./Triangle.png"));
		Sprite triSprite = new Sprite(triTexture);

		float pixelsPerMeter = 100; // Conversion factor from meters to pixels
		float spriteWidth = 1 * pixelsPerMeter;
		float spriteHeight = 1 * pixelsPerMeter;

		triSprite.setSize(spriteWidth, spriteHeight);
		triSprite.setOrigin(spriteWidth / 2, spriteHeight / 2); // Set origin to the center for rotation

		// Debug Cam
		debugRenderer = new Box2DDebugRenderer();
	}

	@Override
	public void render() {
		ScreenUtils.clear(0.0f, 0.4f, 0.8f, 1);

		camera.update();
		debugRenderer.render(world, camera.combined);

		

		batch.begin();
		// batch.draw(img, 0, 0);
		batch.end();
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
			playerBody.setLinearVelocity(0, 40.0f);
			playerBody.setAngularVelocity(-6.0f);
		}

		world.step(1 / 60f, 6, 2);
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}
}
