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

    private static final int SIZE_INCREMENT = 20;
    private static final int AMOUNT = 10;
    private static final int SIZE_START = 100;

    private static class FontNotFoundException extends Exception {
        private FontNotFoundException(String message) {
            super(message);
        }
    }

    private volatile static HashMap<Integer, Font> fonts = new HashMap<Integer, Font>();
    private static FreeTypeFontGenerator generator;
    private static int currentSize = SIZE_START;
    private static boolean finishedLoading = false;

    public static void loadFonts() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal(Data.FONT_LONDON));
        load(currentSize);
    }

    private static void load(final int size){
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                if(size > SIZE_START + AMOUNT * SIZE_INCREMENT) {
                    finishedLoading = true;
                    return;
                }

                FreeTypeFontParameter parameter = new FreeTypeFontParameter();
                parameter.genMipMaps = false;
                parameter.minFilter = Texture.TextureFilter.Linear;
                parameter.magFilter = Texture.TextureFilter.Linear;
                parameter.size = size;

                fonts.put(size, new Font(generator.generateFont(parameter)));

                load(size + SIZE_INCREMENT);
            }
        });

    }

    public static Font get(int size){
        Font f = null;

        try {
            while(f == null) {
                if(size <= 0) break;
                f = fonts.get(size);
                size -= SIZE_INCREMENT;
            }

            if(f == null) throw new FontNotFoundException("Couldn't find font with size: " + size);
        }
        catch (FontNotFoundException e){
            e.printStackTrace();
        }

        return f;
    }

    public static void dispose() {
        for(Font f : fonts.values()){
            f.getBitmapFont().dispose();
        }

        generator.dispose();
    }

    public static boolean finishedLoading() {
        return finishedLoading;
    }

    public static HashMap<Integer, Font> getFonts() {
        return fonts;
    }
}
