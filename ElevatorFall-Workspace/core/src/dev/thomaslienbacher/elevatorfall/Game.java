package dev.thomaslienbacher.elevatorfall;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.thomaslienbacher.elevatorfall.assets.Fonts;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.scene.*;
import dev.thomaslienbacher.elevatorfall.utils.Logger;
import dev.thomaslienbacher.elevatorfall.utils.Mouse;


/**
 * Logo only shows for 0.1 seconds because of easier development change to 3 seconds when shipping
 * 
 * @author Thomas Lienbacher
 */
public class Game extends ApplicationAdapter {
	
	//constants
	public static final int WIDTH = 1080;
	public static final int HEIGHT = WIDTH / 9 * 16;
	public static final float ASPECT_RATIO = (float)WIDTH / (float)HEIGHT;
	public static final String TITLE = "Elevator Fall";

	private static SpriteBatch batch;
	private static OrthographicCamera cam;
	private static OrthographicCamera guiCam;
	private static GameStates gameState = GameStates.STARTUP;
	private static AssetManager assetManager;
	private static boolean firstFrame = true;
	
	//Scenes
	private static StartupScene startupScene;
	private static MenuScene menuScene;
	private static GameScene gameScene;
	private static LoadingScene loadingScene;

	//cursors
	private static Cursor pointer;

	//debug
	public final static boolean DEBUG = false;
	private static ShapeRenderer shapeRenderer;

	@Override
	public void create () {
		assetManager = new AssetManager();
		
		//cursors
		Pixmap pointerPixmap = new Pixmap(Gdx.files.internal(Data.POINTER_SPRITE));
		pointer = Gdx.graphics.newCursor(pointerPixmap, 1, 1);
		pointerPixmap.dispose();
		
		Gdx.graphics.setCursor(pointer);
		
		//cam
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		guiCam = new OrthographicCamera();
		guiCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//resize
		if(Gdx.app.getType() == Application.ApplicationType.Desktop) {
			float d = 0.85f;
			float height = Gdx.graphics.getDisplayMode().height * d;
			float width = height * (1 / ASPECT_RATIO);
			Gdx.graphics.setWindowedMode((int) height, (int) width);
		}

		//batch
		batch = new SpriteBatch();
		
		//setup loadingscene
		startupScene = new StartupScene(GameStates.STARTUP);
		startupScene.loadAssets(assetManager);
		assetManager.finishLoading(); //this should never be called but this is an exception
		startupScene.create(assetManager);
		startupScene.switchTo();

		//debug
		if(DEBUG) {
			shapeRenderer = new ShapeRenderer();
			shapeRenderer.setAutoShapeType(true);
		}
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.5f,0.5f,0.5f,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();

		if(gameState == GameStates.STARTUP){
			startupScene.render(batch);
		}
		
		if(gameState == GameStates.MENU){
			menuScene.render(batch);

			batch.setProjectionMatrix(guiCam.combined);
			menuScene.renderGUI(batch);
			batch.setProjectionMatrix(cam.combined);
		}
		
		if(gameState == GameStates.GAME){
			gameScene.render(batch);
			
			batch.setProjectionMatrix(guiCam.combined);
			gameScene.renderGUI(batch);
			batch.setProjectionMatrix(cam.combined);
		}

		if(gameState == GameStates.LOADING){
			loadingScene.render(batch);

			batch.setProjectionMatrix(guiCam.combined);
			loadingScene.renderGUI(batch);
			batch.setProjectionMatrix(cam.combined);
		}

		batch.end();

		//debug
		if(DEBUG) {
			shapeRenderer.setProjectionMatrix(cam.combined);
			shapeRenderer.begin();
			shapeRenderer.end();
		}

		update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void update(float delta){
		Gdx.graphics.setTitle(TITLE + "   -   " + String.format("%.2f FPS", 1.0f / delta));
		if(delta > 0.2f) delta = 0.2f;

		Mouse.update(cam);

		if(firstFrame){
			//scenes
			menuScene = new MenuScene(GameStates.MENU);
			gameScene = new GameScene(GameStates.GAME);
			loadingScene = new LoadingScene(GameStates.LOADING);

			//load all assets
			Fonts.loadFonts();
			menuScene.loadAssets(assetManager);
			gameScene.loadAssets(assetManager);
			loadingScene.loadAssets(assetManager);
			
			firstFrame = false;
		}
		
		if(gameState == GameStates.STARTUP){
			startupScene.update(delta);
			assetManager.update();
			if(assetManager.getProgress() >= 1 && startupScene.logoTime >= StartupScene.LOGO_DISLAY_TIME
					&& Fonts.isLoaded()){
				menuScene.switchTo();
				startupScene.dispose();

				menuScene.create(assetManager);
				gameScene.create(assetManager);
				loadingScene.create(assetManager);
			}
		}
		
		if(gameState == GameStates.MENU) menuScene.update(delta);
		if(gameState == GameStates.GAME) gameScene.update(delta);
		if(gameState == GameStates.LOADING) loadingScene.update(delta);
	}

	@Override
	public void dispose () {
		menuScene.dispose();
		gameScene.dispose();
		loadingScene.dispose();

		batch.dispose();
		Fonts.dispose();
		assetManager.dispose();

		if(DEBUG) {
			shapeRenderer.dispose();
		}
	}

	public static float getDelta(){
		return Gdx.graphics.getDeltaTime();
	}

	public static SpriteBatch getBatch() {
		return batch;
	}

	public static OrthographicCamera getCam() {
		return cam;
	}

	public static OrthographicCamera getGuiCam() { return guiCam; }

	public static GameStates getGameState() {
		return gameState;
	}

	public static AssetManager getAssetManager() {
		return assetManager;
	}

	public static boolean isFirstFrame() {
		return firstFrame;
	}

	public static StartupScene getStartupScene() {
		return startupScene;
	}

	public static MenuScene getMenuScene() {
		return menuScene;
	}

	public static GameScene getGameScene() {
		return gameScene;
	}

	public static LoadingScene getLoadingScene() {
		return loadingScene;
	}

	public static Cursor getPointer() {
		return pointer;
	}

	public static void setGameState(GameStates gameState) {
		Game.gameState = gameState;
	}

	public static void setPointer(Cursor pointer) {
		Game.pointer = pointer;
	}
}
