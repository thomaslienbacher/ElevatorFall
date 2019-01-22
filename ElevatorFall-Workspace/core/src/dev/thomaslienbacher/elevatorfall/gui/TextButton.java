package dev.thomaslienbacher.elevatorfall.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

/**
 * @author Thomas Lienbacher
 */
public class TextButton extends Button {

    private String text;
    private Font font;
    private Color fontColor;

    public TextButton(Texture up, Texture down, Vector2 position, String text, Font font, Color fontColor, Timer.Task task) {
        super(up, down, position, task);
        this.text = text;
        this.font = font;
        this.fontColor = fontColor;
    }

    public void render(SpriteBatch batch) {
        super.render(batch);

        if(!text.equalsIgnoreCase("")) {
            font.renderCentered(batch, text, position.x + width / 2, position.y + height / 2, fontColor);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
}
