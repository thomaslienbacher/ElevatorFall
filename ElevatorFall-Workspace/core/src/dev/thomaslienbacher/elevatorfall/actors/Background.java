package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class Background extends Actor {

    private Texture backgroundTile;
    private int amount;
    private float[] positions;

    public Background(Texture backgroundTile) {
        Utils.setLinearFilter(backgroundTile);
        this.backgroundTile = backgroundTile;

        amount = (int) Math.ceil(Game.HEIGHT / backgroundTile.getHeight()) + 2;
        positions = new float[amount];

        for(int i = 0; i < amount; i++) {
            positions[i] = i * backgroundTile.getHeight();
        }
    }

    public void reset() {
        for(int i = 0; i < amount; i++) {
            positions[i] = i * backgroundTile.getHeight();
        }
    }

    @Override
    public void update(float delta) {
        float smallest = Game.HEIGHT;

        for(int i = 0; i < amount; i++) {
            positions[i] += delta * CollideBox.SPEED * CollideBoxManager.SPEED_MULTIPLIER;
            if(positions[i] < smallest) smallest = positions[i];
        }

        for(int i = 0; i < amount; i++) {
            if(positions[i] >= Game.HEIGHT) positions[i] = smallest - backgroundTile.getHeight();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for(int i = 0; i < amount; i++) {
            batch.draw(backgroundTile, 0, positions[i]);
        }
    }
}
