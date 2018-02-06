package dev.thomaslienbacher.elevatorfall.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * This represents a linear gauge
 *
 *A gauge can be loaded using the gauge json file
 *
 *
 * Example File:
 * {
 * 	"frame":"gauge_frame.png",
 *	"color":["0.7","0.723","0.344"],
 *	"offset":["10","10"],
 *  "dimension":["240","60"]
 * }
 *
 * @author Thomas Lienbacher
 */
public class LinearGauge {

    /**
     * used to parse the gauge files
     */
    private static JSONParser jsonParser = new JSONParser();

    //Gaugedata
    /**
     * sprite used to frame the value rectangle
     */
    private Sprite frame;

    /**
     * Color used to draw the rectanlge representing the value
     */
    private Color gaugeColor;

    /**
     * The offset of the value rectangle from the bottom left hand corner of the frame
     */
    private Vector2 offset;

    /**
     * Maximum width of the value rectangle
     */
    private float widthBar = 0;

    /**
     * Height of the value rectangle
     */
    private float heightBar = 0;

    /**
     * Number between 0 and 1 used to represent how full the gauge is,
     * it also represents the relativ width of the value rectangle
     */
    public float value = 1;

    /**
     * Texture used to draw and represent the value rectangle
     */
    private Texture valueTexture;

    /**
     * Stores the coordinates to draw the gauge to
     */
    private Vector2 position;

    /**
     *
     * @param frame sprite to draw the aguge in
     * @param gaugeColor color of the drawn bar
     * @param offset offset of the bar from the bottom left
     * @param barSize size of the bar
     * @param position position
     */
    public LinearGauge(Sprite frame, Color gaugeColor, Vector2 offset, Vector2 barSize, Vector2 position) {
        this.frame = frame;
        this.gaugeColor = gaugeColor;
        this.offset = offset;
        this.position = position;
        this.widthBar = barSize.x;
        this.heightBar = barSize.y;

        //generate value texture
        Pixmap gaugePixmap = new Pixmap((int) widthBar, (int) heightBar, Pixmap.Format.RGBA8888);
        gaugePixmap.setColor(gaugeColor);
        gaugePixmap.fillRectangle(0, 0, gaugePixmap.getWidth(), gaugePixmap.getHeight());
        valueTexture = new Texture(gaugePixmap);
        gaugePixmap.dispose();
    }

    public void render(SpriteBatch batch) {
        frame.setPosition(position.x, position.y);

        batch.draw(valueTexture, position.x + offset.x, position.y + offset.y, widthBar * value, heightBar);
        frame.draw(batch);
    }

    //getter and setters

    public Color getGaugeColor() {
        return gaugeColor;
    }

    public void setGaugeColor(Color gaugeColor) {
        this.gaugeColor = gaugeColor;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setPosition(float x, float y) {
        setPosition(new Vector2(x, y));
    }

    public Sprite getFrame() {
        return frame;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public float getWidthBar() {
        return widthBar;
    }

    public float getHeightBar() {
        return heightBar;
    }

    public Texture getValueTexture() {
        return valueTexture;
    }

}