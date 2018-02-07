package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.assets.Fonts;
import dev.thomaslienbacher.elevatorfall.gui.TextButton;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class MenuScene extends Scene {
	
	private TextButton playButton;

	public MenuScene(GameStates state) {
		super(state);
	}

	@Override
	public void loadAssets(AssetManager assetManager) {
		assetManager.load(Data.MENU_BUTTON_DOWN, Texture.class);
		assetManager.load(Data.MENU_BUTTON_UP, Texture.class);
	}

	@Override
	public void create(AssetManager assetManager) {
		playButton = new TextButton((Texture) assetManager.get(Data.MENU_BUTTON_UP),
				(Texture) assetManager.get(Data.MENU_BUTTON_DOWN),
				new Vector2(100, 100), "Play", Fonts.getLondon(60), Color.BLACK, new Task(){
					@Override
					public void run() {
						Game.getGameScene().switchTo();
					}
		});
	}

	@Override
	public void render(SpriteBatch batch) {
		
	}
	
	@Override
	public void renderGUI(SpriteBatch batch){
		Fonts.getMorris(100).render(batch, "WOW", Game.WIDTH / 2 - Utils.calculateStringWidth(Fonts.getMorris(100), "WOW") / 2,
				Game.HEIGHT - 60, Color.BLACK);
		playButton.render(batch);
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
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		playButton.checkTouchDown(screen);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		playButton.checkTouchUp(screen);
		return false;
	}

	//android
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		playButton.checkPointerMoved(screen);
		return false;
	}

	//desktop
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		Vector2 screen = Game.toScreenCoords(screenX, screenY);
		playButton.checkPointerMoved(screen);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}

