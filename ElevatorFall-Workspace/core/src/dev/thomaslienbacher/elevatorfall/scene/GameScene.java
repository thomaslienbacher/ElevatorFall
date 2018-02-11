package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.actors.Background;
import dev.thomaslienbacher.elevatorfall.actors.Ball;
import dev.thomaslienbacher.elevatorfall.actors.Bounds;
import dev.thomaslienbacher.elevatorfall.actors.CollideBoxManager;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.assets.Fonts;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsContactListener;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;

/**
 * @author Thomas Lienbacher
 */
public class GameScene extends Scene {

	private static final float SCORE_MULTIPLIER = 20.0f;

	private PhysicsSpace space;
	private Ball ball;
	private CollideBoxManager collideBoxManager;
    private Bounds bounds;
    private Background background;
    private float score = 0;

	//debug
	Box2DDebugRenderer renderer;

	public GameScene(GameStates state) {
		super(state);
	}
	
	@Override
	public void loadAssets(AssetManager assetManager) {
		assetManager.load(Data.BALL_TEXTURE, Texture.class);
		assetManager.load(Data.COLLIDEBOX_TEXTURE, Texture.class);
		assetManager.load(Data.BACKGROUND_TEXTURE, Texture.class);
	}

	@Override
	public void create(AssetManager assetManager) {
		space = new PhysicsSpace(Data.GRAVITY, new PhysicsContactListener());
		ball = new Ball(space, (Texture) assetManager.get(Data.BALL_TEXTURE));
		collideBoxManager = new CollideBoxManager(space, (Texture) assetManager.get(Data.COLLIDEBOX_TEXTURE));
        bounds = new Bounds(space);
        background = new Background((Texture) assetManager.get(Data.BACKGROUND_TEXTURE));

		//debug
		if(Game.DEBUG) {
			renderer = new Box2DDebugRenderer();
			renderer.setDrawVelocities(true);
			renderer.setDrawBodies(true);
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		background.render(batch);
		ball.render(batch);
		collideBoxManager.render(batch);


		//debug
		if(Game.DEBUG) {
			batch.end();
			batch.begin();
			renderer.render(space.get(), Game.getCam().combined.cpy().scl(Data.MTR_2_PXL));
			batch.end();
			batch.begin();
		}
	}
	
	@Override
	public void renderGUI(SpriteBatch batch){
		Fonts.getMorris(100).render(batch, "" + (int) Math.floor(score), 10, 115, Color.BLACK);
	}

	@Override
	public void update(float delta) {
		space.step(delta, Data.VELOCITY_ITER, Data.POSITION_ITER);
		ball.update(delta);
		collideBoxManager.update(delta);
        background.update(delta);

		score += delta * SCORE_MULTIPLIER;
	}

	@Override
	public void dispose() {
		space.dispose();

		//debug
		if(Game.DEBUG) {
			renderer.dispose();
		}
	}

	public void onDeath(){
		int s = (int) Math.floor(score);
		Game.setHighscore(s > Game.getHighscore() ? s : Game.getHighscore());
		Game.getMenuScene().switchTo();
		reset();
	}

	public void reset() {
		ball.reset();
		collideBoxManager.reset();
		background.reset();
		score = 0;
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
		ball.checkTouchDown(screen, pointer);

		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		ball.checkTouchUp(pointer);

		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		ball.checkPointerMoved(screen, pointer);

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

	public CollideBoxManager getCollideBoxManager() {
		return collideBoxManager;
	}

	public Bounds getBounds() {
		return bounds;
	}

    public Background getBackground() {
        return background;
    }

    public float getScore() {
        return score;
    }
}
