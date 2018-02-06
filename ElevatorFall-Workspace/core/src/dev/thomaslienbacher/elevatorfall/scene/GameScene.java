package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import dev.thomaslienbacher.elevatorfall.assets.Fonts;

/**
 * @author Thomas Lienbacher
 */
public class GameScene extends Scene {

	public GameScene(GameStates state) {
		super(state);

	}
	
	@Override
	public void loadAssets(AssetManager assetManager) {

	}

	@Override
	public void create(AssetManager assetManager) {

	}

	@Override
	public void render(SpriteBatch batch) {

	}
	
	@Override
	public void renderGUI(SpriteBatch batch){
		Fonts.getMorris(45).render(batch, "Defender", 10, 50, Color.BLACK);
	}

	@Override
	public void update(float delta) {

	}

	@Override
	public void dispose() {

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
}
