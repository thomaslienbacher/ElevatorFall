package dev.thomaslienbacher.elevatorfall.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.World;
import dev.thomaslienbacher.elevatorfall.assets.Data;

/**
 * @author Thomas Lienbacher
 */
public class PhysicsSpace {
    private World world;

    public PhysicsSpace(Vector2 gravity, ContactListener contactListener) {
        gravity.scl(Data.PXL_2_MTR);
        world = new World(gravity, true);
        world.setContactListener(contactListener);
    }

    public void step(float delta, int velIter, int posIter){
        world.step(delta, velIter, posIter);
    }

    public World get() {
        return world;
    }

    public void dispose(){
        world.dispose();
    }
}
