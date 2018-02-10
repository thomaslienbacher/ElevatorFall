package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsSpace;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class ControlBar extends PhysicsActor {

    private static float HANDDIFF_X = Game.WIDTH / 2 - 100;

    private Sprite sprite;
    private Vector2 leftHand;
    private Vector2 rightHand;

    public ControlBar(PhysicsSpace space, Texture tex){
        super(true);
        Utils.setLinearFilter(tex);
        this.sprite = new Sprite(tex);
        leftHand = new Vector2(Game.WIDTH / 2 - HANDDIFF_X, 600);
        rightHand = new Vector2(Game.WIDTH / 2 + HANDDIFF_X, 600);

        body.initAsBox(space, BodyDef.BodyType.KinematicBody, new Vector2(Game.WIDTH / 2 - sprite.getWidth() / 2, Game.HEIGHT / 2 - 200),
                Data.FRICTION_DYNAMIC, 1530, sprite.getHeight());
    }

    @Override
    public void update(float delta) {
        float angle = leftHand.cpy().sub(rightHand).angle();

        body.setPositionPxl(new Vector2(Game.WIDTH / 2, (leftHand.y  + rightHand.y) / 2));
        body.setAngle(angle);
    }

    @Override
    public void render(SpriteBatch batch) {
        Vector2 pos = body.getPositionPxl();
        sprite.setPosition(pos.x - sprite.getWidth() / 2, pos.y - sprite.getHeight() / 2);
        sprite.setRotation(body.getAngle());
        sprite.setScale(scale);
        sprite.draw(batch);
    }

    public void checkTouchDown(Vector2 screen) {
        if(screen.x < Game.WIDTH / 2) leftHand.y = screen.y;
        if(screen.x > Game.WIDTH / 2) rightHand.y = screen.y;
    }

    public void checkPointerMoved(Vector2 screen) {
        if(screen.x < Game.WIDTH / 2) leftHand.y = screen.y;
        if(screen.x > Game.WIDTH / 2) rightHand.y = screen.y;
    }
}
