package dev.thomaslienbacher.elevatorfall.scene;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.assets.Fonts;
import dev.thomaslienbacher.elevatorfall.gui.LinearGauge;
import dev.thomaslienbacher.elevatorfall.utils.Animation2D;
import dev.thomaslienbacher.elevatorfall.utils.MethodContainer;
import dev.thomaslienbacher.elevatorfall.utils.RunnableContainer;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class LoadingScene extends Scene {
    private Texture background = null;
    private Animation2D loadingAnimation = null;
    private LinearGauge loadingGauge = null;
    private GameStates previousState = null;

    public LoadingScene(GameStates state) {
        super(state);
    }

    public void load(MethodContainer methodContainer, GameStates state){
        previousState = state;
        Thread t = new Thread(new RunnableContainer(methodContainer) {
            @Override
            public void run() {
                Game.setGameState(GameStates.LOADING);
                ((MethodContainer)refs[0]).method();
            }
        });
        t.start();
    }

    @Override
    public void loadAssets(AssetManager assetManager) {
        assetManager.load(Data.LOADING_GAUGE, Texture.class);
        assetManager.load(Data.LOADING_BACKGROUND, Texture.class);
        assetManager.load(Data.LOADING_ANIMATION, TextureAtlas.class);
    }

    @Override
    public void create(AssetManager assetManager) {
        Sprite frame = new Sprite((Texture) assetManager.get(Data.LOADING_GAUGE));
        frame.getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        loadingGauge = new LinearGauge(frame, Data.TLS_BLUE, new Vector2(8, 8), new Vector2(1084, 28), new Vector2(Game.WIDTH / 2 - frame.getWidth() / 2, 50));
        background = assetManager.get(Data.LOADING_BACKGROUND);
        background.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        loadingAnimation = new Animation2D((TextureAtlas)assetManager.get(Data.LOADING_ANIMATION), 0.01f, true, new Vector2());
        loadingAnimation.setScale(0.6f);
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void renderGUI(SpriteBatch batch) {
        batch.draw(background, 0, 0);
        loadingGauge.render(batch);
        loadingAnimation.render(batch);
        float width = Utils.calculateStringWidth(Fonts.getLondon(92), "Loading");
        Fonts.getLondon(92).render(batch, "Loading", Game.WIDTH / 2 - width / 2, 500, Color.BLACK);
    }

    @Override
    public void update(float delta) {
        Game.getAssetManager().update();
        loadingGauge.value = Game.getAssetManager().getProgress();
        if(Game.getAssetManager().getProgress() >= 1) Game.setGameState(previousState);
        loadingAnimation.update(delta);
        loadingAnimation.setPosition(Game.WIDTH / 2 - loadingAnimation.getKeySprite().getWidth() / 2, 120);
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
