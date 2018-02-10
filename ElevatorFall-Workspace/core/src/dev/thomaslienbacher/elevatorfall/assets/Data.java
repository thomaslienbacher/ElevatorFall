package dev.thomaslienbacher.elevatorfall.assets;

import com.badlogic.gdx.math.Vector2;

/**
 * @author Thomas Lienbacher
 */
public class Data {

    //colors
    //public static final Color TLS_BLUE = new Color((float)117 / 255, (float)183 / 255, 1.0f, 1.0f);

    //constants
    public static final float PXL_2_MTR = 0.1f;
    public static final float MTR_2_PXL = 1 / PXL_2_MTR;
    public static final float FRICTION_DYNAMIC = 0.3f;
    public static final float FRICTION_STATIC = 0.3f;
    public static final int VELOCITY_ITER = 4;
    public static final int POSITION_ITER = 2;
    public static final Vector2 GRAVITY = new Vector2(0, -170);

    //misc
    public static final String DEV_LOGO = "logo.png";

    //gui
    public static final String MENU_BUTTON_UP = "gui/buttons/menu_button_up.png";
    public static final String MENU_BUTTON_DOWN = "gui/buttons/menu_button_down.png";
    public static final String LOADING_GAUGE = "loading/loading_gauge.png";
    public static final String LOADING_BACKGROUND = "loading/loading_background.png";
    public static final String LOADING_ANIMATION = "loading/loading_animation.pack";

    //actors
    public static final String BALL_TEXTURE = "actors/ball.png";
    public static final String COLLIDEBOX_TEXTURE = "actors/collidebox.png";

    //fonts
    public static final String FONT_LONDON = "fonts/LondonTwo.ttf";
    public static final String FONT_MORRIS = "fonts/MorrisRomanBlack.ttf";
}
