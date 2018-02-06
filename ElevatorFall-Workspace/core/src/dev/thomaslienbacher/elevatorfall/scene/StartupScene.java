package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.thomaslienbacher.elevatorfall.assets.Data;


/**
 * This scene is drawn while the game loads up all the assets.
 * 
 * @author Thomas Lienbacher
 */
public class StartupScene extends Scene {
	
	//constants
	public static final float LOGO_DISLAY_TIME = 0.1f;//set this to 3
	
	public float logoTime = 0;//counts how many seconds the logo has been displayed
	public Texture tlsLogo;//Thomas Lienbacher Software Logo

	public StartupScene(GameStates state) {
		super(state);
	}
	
	@Override
	public void loadAssets(AssetManager assetManager) {
		assetManager.load(Data.TLS_LOGO, Texture.class);
	}

	@Override
	public void create(AssetManager assetManager) {
		tlsLogo = assetManager.get(Data.TLS_LOGO);
		tlsLogo.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(tlsLogo, 0, 0);
	}
	
	@Override
	public void renderGUI(SpriteBatch batch){

	}

	@Override
	public void update(float delta) {
		logoTime += delta;
	}

	@Override
	public void dispose() {
		tlsLogo.dispose();
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
