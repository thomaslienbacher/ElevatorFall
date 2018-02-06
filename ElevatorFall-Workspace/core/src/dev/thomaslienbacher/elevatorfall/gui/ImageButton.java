package dev.thomaslienbacher.elevatorfall.gui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class ImageButton extends Button {
    private Sprite image;
    private Vector2 imageOffset;

    public ImageButton(Texture up, Texture down, Vector2 position, Texture image, Timer.Task task) {
        super(up, down, position, task);
        Utils.setLinearFilter(image);
        this.image = new Sprite(image);
        this.imageOffset = new Vector2();
    }

    public ImageButton(Texture up, Texture down, Vector2 position, Texture image, Vector2 imageOffset, Timer.Task task) {
        this(up, down, position, image, task);
        this.imageOffset = imageOffset;
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        image.setPosition(position.x + width / 2 - image.getWidth() / 2 + imageOffset.x,
                position.y + height / 2 - image.getHeight() / 2 + imageOffset.y);
        image.draw(batch);
    }

    public Sprite getImage() {
        return image;
    }

    public void setImage(Sprite image) {
        this.image = image;
    }

    public Vector2 getImageOffset() {
        return imageOffset;
    }

    public void setImageOffset(Vector2 imageOffset) {
        this.imageOffset = imageOffset;
    }
}
