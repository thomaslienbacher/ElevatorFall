package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.FontManager;
import dev.thomaslienbacher.elevatorfall.utils.Blurring;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class PauseScene extends Scene {

    private GameScene gameScene;
    private FrameBuffer frameBuffer;
    private Texture blurredTexture;

    public PauseScene(GameStates state, GameScene gameScene) {
        super(state);
        this.gameScene = gameScene;
    }

    @Override
    public void loadAssets(AssetManager assetManager) {

    }

    @Override
    public void switchTo() {
        super.switchTo();

        if(blurredTexture != null) {
            blurredTexture.dispose();
            blurredTexture = null;
        }
    }

    @Override
    public void create(AssetManager assetManager) {
        int w = Game.WIDTH / 32;
        int h = Game.HEIGHT / 32;

        this.frameBuffer = new FrameBuffer(Pixmap.Format.RGB565, w, h, false);
    }

    @Override
    public void render(SpriteBatch batch) {
        if(blurredTexture == null) {
            Gdx.app.log("blur", "started");
            long start = System.currentTimeMillis();
            frameBuffer.begin();
            batch.setProjectionMatrix(Game.getCam().combined);
            gameScene.render(batch);
            batch.setProjectionMatrix(Game.getGuiCam().combined);
            gameScene.renderGUI(batch);
            batch.flush();
            frameBuffer.end();

            Pixmap pixmap = Utils.getPixmapFromFramebuffer(frameBuffer);
            blurredTexture = Blurring.blurTexture(pixmap);
            pixmap.dispose();

            Utils.setLinearFilter(blurredTexture);

            Gdx.app.log("blur", "ended: " + (System.currentTimeMillis() - start));
        }
    }

    @Override
    public void renderGUI(SpriteBatch batch) {
        batch.draw(blurredTexture, 0, 0, Game.WIDTH, Game.HEIGHT, 0, 0, blurredTexture.getWidth(), blurredTexture.getHeight(), false, true);
        FontManager.get(200).renderCentered(batch, "Paused", Game.WIDTH / 2, Game.HEIGHT / 2, Color.BLACK);
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        if(blurredTexture != null) blurredTexture.dispose();
        frameBuffer.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK || keycode == Input.Keys.BACKSPACE) Game.getGameScene().switchTo();

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
