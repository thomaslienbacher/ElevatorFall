package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import dev.thomaslienbacher.elevatorfall.Game;
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
        super(false);
        Utils.setLinearFilter(tex);
        this.sprite = new Sprite(tex);

        body.initAsCircle(space, BodyDef.BodyType.DynamicBody, new Vector2(Game.WIDTH / 2, Game.HEIGHT / 2), Data.FRICTION_DYNAMIC, sprite.getWidth() / 2);
    }

    @Override
    public void update(float delta) {

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
