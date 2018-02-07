package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class Ball extends PhysicsActor {

    private Sprite sprite;
    private boolean dead = false;

    public Ball(PhysicsSpace space, Texture tex){
        Utils.setLinearFilter(tex);
        this.sprite = new Sprite(tex);

        body.initAsCircle(space, BodyDef.BodyType.KinematicBody, new Vector2(), Data.FRICTION_DYNAMIC, sprite.getWidth()); //TODO: change
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(SpriteBatch batch) {
        Vector2 pos = body.getPositionPxl();
        sprite.setPosition(pos.x, pos.y);
        sprite.setRotation(rotation);
        sprite.setScale(scale);
        sprite.draw(batch);
    }
}
