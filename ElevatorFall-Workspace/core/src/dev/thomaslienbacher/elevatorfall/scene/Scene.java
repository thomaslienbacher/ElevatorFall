package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import dev.thomaslienbacher.elevatorfall.Game;

/**
 * @author Thomas Lienbacher
 */
public abstract class Scene implements InputProcessor{
	
	private GameStates state;
	
	public Scene(GameStates state){
		this.state = state;
	}
	
	public abstract void loadAssets(AssetManager assetManager);

	public abstract void create(AssetManager assetManager);
	
	public abstract void render(SpriteBatch batch);
	
	public abstract void renderGUI(SpriteBatch batch);
	
	public abstract void update(float delta);
	
	public abstract void dispose();
	
	public void switchTo() {
		Game.setGameState(this.state);
		Gdx.input.setInputProcessor(this);
	}

	public GameStates getState() {
		return state;
	}
}
