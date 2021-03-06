package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.assets.FontManager;
import dev.thomaslienbacher.elevatorfall.gui.TextButton;

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
                Vector2.Zero, "Play", FontManager.get(110), Color.BLACK, new Task() {
            @Override
            public void run() {
                Game.getGameScene().switchTo();
            }
        });
        playButton.setPosition(Game.WIDTH / 2 - playButton.getSpriteUp().getWidth() / 2, Game.HEIGHT / 2 - 500);
    }

    @Override
    public void render(SpriteBatch batch) {
        FontManager.get(80).renderCentered(batch, "Highscore: " + Game.getHighscore(), Game.WIDTH / 2, 1300, Color.BLACK);
    }

    @Override
    public void renderGUI(SpriteBatch batch) {
        FontManager.get(200).renderCentered(batch, Game.APP_NAME, Game.WIDTH / 2, Game.HEIGHT - 200, Color.BLACK);
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
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) Gdx.app.exit();

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
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}

