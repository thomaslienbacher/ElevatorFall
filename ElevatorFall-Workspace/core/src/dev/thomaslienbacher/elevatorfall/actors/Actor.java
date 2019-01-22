package dev.thomaslienbacher.elevatorfall.actors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Thomas Lienbacher
 */
public abstract class Actor {

    protected float width = 0;
    protected float height = 0;
    protected float rotation = 0;
    protected float scale = 1;
    protected Vector2 position = new Vector2();

    public Actor() {}

    public abstract void update(float delta);

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
    }

    public void setPosition(Vector2 v) {
        this.position.x = v.x;
        this.position.y = v.y;
    }

    public void setPositionX(float x) {
        this.position.x = x;
    }

    public void setPositionY(float y) {
        this.position.y = y;
    }

    public void translate(float x, float y) {
        this.position.x += x;
        this.position.y += y;
    }

    public void translate(Vector2 v) {
        this.position.x += v.x;
        this.position.y += v.y;
    }

    public void translateX(float x) {
        this.position.x += x;
    }

    public void translateY(float y) {
        this.position.y += y;
    }

    public void setRotation(float degrees) {
        this.rotation = degrees;
    }

    public void rotate(float degrees) {
        this.rotation += degrees;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void scaleMul(float mul) {
        this.scale *= scale;
    }

    public abstract void render(SpriteBatch batch);

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return rotation;
    }

    public float getScale() {
        return scale;
    }

    public Vector2 getPosition() {
        return position;
    }
}
