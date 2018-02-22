package dev.thomaslienbacher.elevatorfall.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import dev.thomaslienbacher.elevatorfall.gui.Font;
import dev.thomaslienbacher.elevatorfall.utils.MethodContainer;
import dev.thomaslienbacher.elevatorfall.utils.RunnableContainer;

import java.util.HashMap;

/**
 * This class contains and manages all fonts used in the game.
 *
 * @author Thomas Lienbacher
 */
public class Fonts {

    private static final int BASE_SIZE = 100;

    private static FreeTypeFontGenerator generator;
    private static BitmapFont font;

    public static void loadFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Data.FONT_LONDON));
        font = load(generator, BASE_SIZE);
    }

    private static BitmapFont load(FreeTypeFontGenerator generator, final int size){
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = size;

        return generator.generateFont(parameter);
    }

    public static Font get(int size){
        return new Font(font, (float)size / (float)BASE_SIZE);
    }

    public static void dispose() {
        font.dispose();
        generator.dispose();
    }
}
