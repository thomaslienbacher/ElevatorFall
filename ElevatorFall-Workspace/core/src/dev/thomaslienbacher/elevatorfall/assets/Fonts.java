package dev.thomaslienbacher.elevatorfall.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import dev.thomaslienbacher.elevatorfall.gui.Font;
import dev.thomaslienbacher.elevatorfall.utils.Logger;

import java.util.HashMap;

/**
 * This class contains and manages all fonts used in the game.
 *
 * @author Thomas Lienbacher
 */
public class Fonts {

    private static final int SIZE_INCREMENT = 15;
    private static final int AMOUNT = 11;

    public static class FontNotFoundException extends Exception {
        public FontNotFoundException() {}

        public FontNotFoundException(String message) {
            super(message);
        }

        public FontNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public FontNotFoundException(Throwable cause) {
            super(cause);
        }
    }

    private static HashMap<Integer, Font> londonFonts = new HashMap<Integer, Font>();
    private static HashMap<Integer, Font> morrisFonts = new HashMap<Integer, Font>();
    private static volatile boolean loaded = false;

    public static void loadFonts() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                Fonts.loadThread();
                loaded = true;
            }
        });
    }

    private static void loadThread(){
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.genMipMaps = false;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;

        FreeTypeFontGenerator londonGen = new FreeTypeFontGenerator(Gdx.files.internal(Data.FONT_LONDON));

        for(int s = SIZE_INCREMENT; s <= SIZE_INCREMENT * AMOUNT; s += SIZE_INCREMENT){
            parameter.size = s;
            londonFonts.put(s, new Font(londonGen.generateFont(parameter)));
        }

        londonGen.dispose();

        FreeTypeFontGenerator morrisGen = new FreeTypeFontGenerator(Gdx.files.internal(Data.FONT_MORRIS));

        for(int s = SIZE_INCREMENT; s <= SIZE_INCREMENT * AMOUNT; s += SIZE_INCREMENT){
            parameter.size = s;
            morrisFonts.put(s, new Font(morrisGen.generateFont(parameter)));
        }

        morrisGen.dispose();

        loaded = true;
    }

    public static Font getLondon(int size){
        Font f = null;

        try {
            f = londonFonts.get(size);
            if(f == null) throw new FontNotFoundException("Couldn't find London font with size: " + size);
        }
        catch (FontNotFoundException e){
            e.printStackTrace();
        }

        return f;
    }

    public static Font getMorris(int size){
        Font f = null;

        try {
            f = morrisFonts.get(size);
            if(f == null) throw new FontNotFoundException("Couldn't find Morris font with size: " + size);
        }
        catch (FontNotFoundException e){
            e.printStackTrace();
        }

        return f;
    }

    public static void dispose() {
        for(Font f : londonFonts.values()){
            f.getBitmapFont().dispose();
        }

        for(Font f : morrisFonts.values()){
            f.getBitmapFont().dispose();
        }
    }

    public static boolean isLoaded() {
        return loaded;
    }
}
