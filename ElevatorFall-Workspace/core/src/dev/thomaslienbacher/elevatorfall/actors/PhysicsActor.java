package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import dev.thomaslienbacher.elevatorfall.physics.PhysicsBody;

/**
 * @author Thomas Lienbacher
 */
public abstract class PhysicsActor{

    public static final float OBJECT_RADIUS = 31.5f;
    
    protected float rotation = 0;
    protected float scale = 1;
    protected PhysicsBody body = new PhysicsBody();
    
    public PhysicsActor(){

    }

    public abstract void update(float delta);

    public void setPositionPxl(float x, float y){
        this.body.setPositionPxl(new Vector2(x, y));
    }

    public void setPositionPxl(Vector2 v){
        this.body.setPositionPxl(v);
    }

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
    
    public Vector2 getPositionPxl(){
        return body.getPositionPxl();
    }
    
    public void applyImpulse(Vector2 impulse){
        body.applyImpulse(impulse);
    }

    public void applyForce(Vector2 force){
        body.applyForce(force);
    }
}
