package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.LinkedList;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;

/**
 * @author Thomas Lienbacher
 */
public class CollideBoxManager {

    private static final float SPAWN_TIME = 4.0f;

    private LinkedList<CollideBox> collideBoxes;
    private PhysicsSpace space;
    private Texture collideBoxTex;
    private float time;

    public CollideBoxManager(PhysicsSpace space, Texture collideBoxTex) {
        this.space = space;
        this.collideBoxTex = collideBoxTex;
        collideBoxes = new LinkedList<CollideBox>();
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
        CollideBox cb = new CollideBox(space, collideBoxTex, (float) Math.floor(Math.random() * Game.WIDTH - collideBoxTex.getWidth()));
        collideBoxes.add(cb);
    }

}
