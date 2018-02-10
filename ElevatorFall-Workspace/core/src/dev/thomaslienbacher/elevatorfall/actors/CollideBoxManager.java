package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class CollideBoxManager {

    private static final float SPAWN_TIME = 1.5f;
    private static final float START_TIME = 2.0f;
    private static final int SPAWN_POSITIONS = 7;

    private LinkedList<CollideBox> collideBoxes;
    private PhysicsSpace space;
    private Texture collideBoxTex;
    private float time;
    private float[] spawnPositions;

    public CollideBoxManager(PhysicsSpace space, Texture collideBoxTex) {
        this.space = space;
        this.collideBoxTex = collideBoxTex;
        collideBoxes = new LinkedList<CollideBox>();
        this.time = START_TIME;

        spawnPositions = new float[SPAWN_POSITIONS];

        for(int i = 0; i < spawnPositions.length; i++) {
            spawnPositions[i] = (int) Math.round((float)i * ((float)(Game.WIDTH - collideBoxTex.getWidth()) / (float)(SPAWN_POSITIONS - 1)));
        }
    }

    public void update(float delta) {
        for(CollideBox c : collideBoxes) c.update(delta);

        for(int i = 0; i < collideBoxes.size(); i++) {
            CollideBox c = collideBoxes.get(i);
            if(c.getBody().getPositionPxl().y > Game.HEIGHT + 100) {
                space.destroyBody(c.body);
                collideBoxes.remove(i);
            }
        }

        time -= delta;

        if(time <= 0) {
            spawn();
            time = SPAWN_TIME;
        }
    }

    public void render(SpriteBatch batch) {
        for(CollideBox c : collideBoxes) c.render(batch);
    }

    private void spawn(){
        CollideBox cb = new CollideBox(space, collideBoxTex, spawnPositions[(int) Math.floor(Math.random() * spawnPositions.length)]);
        collideBoxes.add(cb);
    }

    public void reset(){
        for(CollideBox c : collideBoxes) space.destroyBody(c.body);

        collideBoxes.clear();
        time = START_TIME;
    }

}
