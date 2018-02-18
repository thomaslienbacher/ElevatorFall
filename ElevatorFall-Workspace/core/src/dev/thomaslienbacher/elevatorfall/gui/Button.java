package dev.thomaslienbacher.elevatorfall.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer.Task;

import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 *         <p>
 *         To use the button {@link #checkTouchDown(Vector2)}, {@link #checkTouchUp(Vector2)}
 *         and {@link #checkPointerMoved(Vector2)} must be placed in their equvilant {@link com.badlogic.gdx.InputProcessor} methods
 */
public class Button {

    public static final float DEFAULT_HOVERSCALE = 0.95f;

    protected Sprite spriteUp;
    protected Sprite spriteDown;
    protected boolean down = false;
    protected boolean hovering = false;
    protected float hoverScale = DEFAULT_HOVERSCALE;
    protected Task onClickTask;

    protected Vector2 position;
    protected float width = 0;
    protected float height = 0;
    protected Rectangle hitbox;

    public Button(Texture up, Texture down, Vector2 position, Task task) {
        spriteUp = new Sprite(up);
        Utils.setLinearFilter(spriteUp.getTexture());
        spriteDown = new Sprite(down);
        Utils.setLinearFilter(spriteDown.getTexture());
        this.onClickTask = task;
        this.position = position.cpy();
        this.width = spriteUp.getWidth() > spriteDown.getWidth() ? spriteUp.getWidth() : spriteDown.getWidth();
        this.height = spriteUp.getHeight() > spriteDown.getHeight() ? spriteUp.getHeight() : spriteDown.getHeight();
        this.hitbox = new Rectangle(position.x, position.y, width, height);

        this.spriteUp.setOriginCenter();
        this.spriteDown.setOriginCenter();
    }

    public void checkTouchUp(Vector2 pos) {
        if(down) {
            if(hitbox.contains(pos.x, pos.y)) onClickTask.run();
            down = false;
        }

        hovering = false;
    }

    public void checkTouchDown(Vector2 pos) {
        if(hitbox.contains(pos.x, pos.y)) {
            down = true;
            hovering = true;
        }
    }

    public void checkPointerMoved(Vector2 pos) {
        hovering = hitbox.contains(pos.x, pos.y);
    }

    public void render(SpriteBatch batch) {
        spriteUp.setPosition(position.x, position.y);
        spriteDown.setPosition(position.x, position.y);

        if(hoverScale != 1f) {
            if(hovering) {
                spriteUp.setScale(hoverScale);
                spriteDown.setScale(hoverScale);
            } else {
                spriteUp.setScale(1f);
                spriteDown.setScale(1f);
            }
        }

        if(!down) spriteUp.draw(batch);
        else spriteDown.draw(batch);
    }

    public Sprite getSpriteUp() {
        return spriteUp;
    }

    public Sprite getSpriteDown() {
        return spriteDown;
    }

    public boolean isDown() {
        return down;
    }

    public Task getOnClickTask() {
        return onClickTask;
    }

    public float getHoverScale() {
        return hoverScale;
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setOnClickTask(Task onClickTask) {
        this.onClickTask = onClickTask;
    }

    public void setHoverScale(float hoverScale) {
        this.hoverScale = hoverScale;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
        hitbox.setPosition(position);
    }

    public void setPosition(float x, float y) {
        this.position.x = x;
        this.position.y = y;
        hitbox.setPosition(x, y);
    }
}
