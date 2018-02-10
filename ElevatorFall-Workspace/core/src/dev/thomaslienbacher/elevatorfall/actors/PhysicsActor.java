package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import dev.thomaslienbacher.elevatorfall.physics.PhysicsBody;

/**
 * @author Thomas Lienbacher
 */
public abstract class PhysicsActor{

    protected float rotation = 0;
    protected float scale = 1;
    protected PhysicsBody body;

    public PhysicsActor(boolean canRotate, String userdata){
        body = new PhysicsBody(canRotate, userdata);
    }

    public abstract void update(float delta);

    public void setRotation(float degrees){
        this.rotation = degrees;
    }

    public void rotate(float degrees){
        this.rotation += degrees;
    }

    public void setScale(float scale){
        this.scale = scale;
    }

    public void scaleMul(float mul){
        this.scale *= scale;
    }

    public abstract void render(SpriteBatch batch);

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public PhysicsBody getBody() {
        return body;
    }

}
