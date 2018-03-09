package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.Pool;

import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class CollideBox extends PhysicsActor {

    public static final String USERDATA = "COLLIDEBOX";
    public static final float SPEED = 800.0f;

    private Sprite sprite;

    public CollideBox(PhysicsSpace space, Texture tex, float x){
        super(false, USERDATA);
        Utils.setLinearFilter(tex);
        this.sprite = new Sprite(tex);

        body.initAsBox(space, BodyDef.BodyType.KinematicBody, new Vector2(x, -100 - sprite.getHeight()), Data.FRICTION_DYNAMIC, sprite.getWidth(), sprite.getHeight());
        body.setLinearVelocity(new Vector2(0, SPEED * CollideBoxManager.SPEED_MULTIPLIER));
    }

    @Override
    public void update(float delta) {
        body.setLinearVelocity(new Vector2(0, SPEED * CollideBoxManager.SPEED_MULTIPLIER));
    }

    @Override
    public void render(SpriteBatch batch) {
        Vector2 pos = body.getPositionPxl();
        sprite.setPosition(pos.x - sprite.getWidth() / 2, pos.y - sprite.getWidth() / 2);
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        sprite.draw(batch);
    }
}
