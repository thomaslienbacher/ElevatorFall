package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsBody;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;

/**
 * @author Thomas Lienbacher
 */
public class Bounds {

    public static final String WALL_USERDATA = "WALL";
    public static final String FLOOR_USERDATA = "FLOOR";

    private PhysicsBody leftWall;
    private PhysicsBody rightWall;
    private PhysicsBody floor;

    public Bounds(PhysicsSpace space) {
        leftWall = new PhysicsBody();
        rightWall = new PhysicsBody();
        floor = new PhysicsBody();

        float t = 30.0f;
        float y = Ball.START_Y - t;

        leftWall.initAsBox(space, BodyDef.BodyType.StaticBody, new Vector2(-t, y),
                Data.FRICTION_STATIC, t, Game.HEIGHT);
        leftWall.get().setUserData(WALL_USERDATA);

        rightWall.initAsBox(space, BodyDef.BodyType.StaticBody, new Vector2(Game.WIDTH, y),
                Data.FRICTION_STATIC, t, Game.HEIGHT);
        rightWall.get().setUserData(WALL_USERDATA);

        floor.initAsBox(space, BodyDef.BodyType.StaticBody, new Vector2(0, y - t),
                Data.FRICTION_STATIC, Game.WIDTH, t);
        floor.get().setUserData(FLOOR_USERDATA);
    }

    public PhysicsBody getLeftWall() {
        return leftWall;
    }

    public PhysicsBody getRightWall() {
        return rightWall;
    }

    public PhysicsBody getFloor() {
        return floor;
    }
}