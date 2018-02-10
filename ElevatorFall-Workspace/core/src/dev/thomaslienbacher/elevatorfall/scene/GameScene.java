package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.Logger;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.actors.Ball;
import dev.thomaslienbacher.elevatorfall.actors.ControlBar;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.assets.Fonts;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsContactListener;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;

/**
 * @author Thomas Lienbacher
 */
public class GameScene extends Scene {

	private PhysicsSpace space;
	private Ball ball;
	private ControlBar controlBar;

	//debug
	Box2DDebugRenderer renderer;

	public GameScene(GameStates state) {
		super(state);
	}
	
	@Override
	public void loadAssets(AssetManager assetManager) {
		assetManager.load(Data.BALL_TEXTURE, Texture.class);
		assetManager.load(Data.CONTROLBAR_TEXTURE, Texture.class);
	}

	@Override
	public void create(AssetManager assetManager) {
		space = new PhysicsSpace(Data.GRAVITY, new PhysicsContactListener());
		ball = new Ball(space, (Texture) assetManager.get(Data.BALL_TEXTURE));
		controlBar = new ControlBar(space, (Texture) assetManager.get(Data.CONTROLBAR_TEXTURE));

		//debug
		if(Game.DEBUG) {
			renderer = new Box2DDebugRenderer();
			renderer.setDrawVelocities(true);
			renderer.setDrawBodies(true);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		ball.render(batch);
		controlBar.render(batch);

		//debug
		if(Game.DEBUG) {
			batch.end();
			batch.begin();
			renderer.render(space.get(), Game.getCam().combined.cpy().scl(Data.MTR_2_PXL));
		}
	}
	
	@Override
	public void renderGUI(SpriteBatch batch){
		Fonts.getMorris(100).render(batch, "Defender", 10, 110, Color.BLACK);
	}

	@Override
	public void update(float delta) {
		space.step(delta, Data.VELOCITY_ITER, Data.POSITION_ITER);
		ball.update(delta);
		controlBar.update(delta);
	}

	@Override
	public void dispose() {
		space.dispose();

		//debug
		if(Game.DEBUG) {
			renderer.dispose();
		}
	}

	@Override
	public boolean keyDown(int keycode) {

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {

		return false;
	}

	@Override
	public boolean keyTyped(char character) {

		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		controlBar.checkTouchDown(screen);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		controlBar.checkPointerMoved(screen);

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {

		return false;
	}

	@Override
	public boolean scrolled(int amount) {
	
		return false;
	}

	public PhysicsSpace getSpace() {
		return space;
	}

	public Ball getBall() {
		return ball;
	}
}
