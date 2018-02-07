package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.actors.Ball;
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

	public GameScene(GameStates state) {
		super(state);
	}
	
	@Override
	public void loadAssets(AssetManager assetManager) {
		assetManager.load(Data.BALL_TEXTURE, Texture.class);
	}

	@Override
	public void create(AssetManager assetManager) {
		space = new PhysicsSpace(new Vector2(), new PhysicsContactListener());
		ball = new Ball(space, (Texture) assetManager.get(Data.BALL_TEXTURE));
	}

	@Override
	public void render(SpriteBatch batch) {
		ball.render(batch);
	}
	
	@Override
	public void renderGUI(SpriteBatch batch){
		Fonts.getMorris(100).render(batch, "Defender", 10, 50, Color.BLACK);
	}

	@Override
	public void update(float delta) {
		space.step(delta, 8, 4);
		ball.update(delta);
	}

	@Override
	public void dispose() {
		space.dispose();
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

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);

		//debug
		ball.setPositionPxl(screen);

		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);

		//debug
		ball.setPositionPxl(screen);

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
