package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.logging.Logger;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class Ball extends PhysicsActor {

    public static final String USERDATA = "BALL";
    public static final float START_Y = Game.HEIGHT / 2 + 200.0f;
    private static final float THRUST = 1300.0f;

    private Sprite sprite;
    private boolean leftThrust = false;
    private boolean rightThrust = false;

    public Ball(PhysicsSpace space, Texture tex){
        super(false, USERDATA);
        Utils.setLinearFilter(tex);
        this.sprite = new Sprite(tex);

        body.initAsCircle(space, BodyDef.BodyType.DynamicBody, new Vector2(Game.WIDTH / 2, START_Y + sprite.getWidth() / 2), Data.FRICTION_DYNAMIC, sprite.getWidth() / 2);
        body.setLinearVelocity(Vector2.Zero);
        body.setRestitution(0.2f);
    }

    @Override
    public void update(float delta) {
        if(leftThrust) body.applyImpulse(new Vector2(-THRUST, 0));
        if(rightThrust) body.applyImpulse(new Vector2(THRUST, 0));
    }

    @Override
    public void render(SpriteBatch batch) {
        Vector2 pos = body.getPositionPxl();
        sprite.setPosition(pos.x - sprite.getWidth() / 2, pos.y - sprite.getWidth() / 2);
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        sprite.draw(batch);
    }

    public void reset(){
        body.setLinearVelocity(Vector2.Zero);
        body.setPositionPxl(new Vector2(Game.WIDTH / 2, START_Y + sprite.getWidth() / 2));
    }

    public void checkTouchUp(Vector2 screen) {
        if(screen.x < Game.WIDTH / 2) leftThrust = false;
        if(screen.x > Game.WIDTH / 2) rightThrust = false;
    }

    public void checkTouchDown(Vector2 screen) {
        if(screen.x < Game.WIDTH / 2) leftThrust = true;
        if(screen.x > Game.WIDTH / 2) rightThrust = true;
    }
}
