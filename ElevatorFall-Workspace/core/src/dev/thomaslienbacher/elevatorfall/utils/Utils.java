package dev.thomaslienbacher.elevatorfall.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.ScreenUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import dev.thomaslienbacher.elevatorfall.gui.Font;

/**
 * Holds a couple of utilities.
 *
 * @author Thomas Lienbacher
 */
public class Utils {

    public static final float DEG_2_RAD = (float)(Math.PI / 180);
    public static final float RAD_2_DEG = (float)(1 / Math.PI * 180);

    //calculates the WIDTH of the string in the given font
    public static float calculateStringWidth(Font font, String string){
        GlyphLayout g = new GlyphLayout();
        g.setText(font.getBitmapFont(), string);
        return g.width;
    }

    //calculates the HEIGHT of the string in the given font
    public static float calculateStringHeight(Font font, String string){
        GlyphLayout g = new GlyphLayout();
        g.setText(font.getBitmapFont(), string);
        return g.height * font.getScale();//TODO debug: file bug report at libgdx repo
    }

    //replace string in every string in an array
    public static String[] replaceStringArray(String array[], String target, String replacement){
    	String replacedArray[] = array;

    	for(int i = 0; i < array.length; i++){
    		replacedArray[i] = replacedArray[i].replaceAll(target, replacement);
    	}

    	return replacedArray;
    }

    /**
     * Method used to get the path of a class file if its a standalone class file.
     *
     * @param c the class to get the path from
     * @return the path of the folder where the class file lies
     */
    public static String getPathOfClass(Class c){
        String path = "";
        path += System.getProperty("java.class.path") + "\\";
        path += c.getName().replace(c.getSimpleName(), "");
        path = path.substring(0, path.length()-1);
        path = path.replace(".", "\\");

        return path;
    }

    /**
     * Method used to get the path of the compiled jar file.
     *
     * Example: C:\Folder\Subfolder\runnable.jar
     *
     * @param c any class which is in the jar file
     * @return the path of the jar file
     */
    public static String getPathOfJar(Class c){
        String encodedPath = c.getProtectionDomain().getCodeSource().getLocation().getPath();
        String path = "";

        try {
            path = URLDecoder.decode(encodedPath, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        path = path.replaceFirst("/", "");
        path = path.replace("/", "\\");

        return path;
    }

    //donwloads a file from the internet and saves it in destination
    public void downloadFile(String urlString, File destination) {
        try {
            URL website = new URL(urlString);
            ReadableByteChannel rbc;
            rbc = Channels.newChannel(website.openStream());
            FileOutputStream fos = new FileOutputStream(destination);
            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            fos.close();
            rbc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Texture blurTexture(Pixmap pixmap) {
        Color[][] pixels = new Color[pixmap.getWidth()][pixmap.getHeight()];
        Color[][] horiPixels = new Color[pixmap.getWidth()][pixmap.getHeight()];


        //fill in pixels
        for(int x = 0; x < pixmap.getWidth(); x++) {
            for(int y = 0; y < pixmap.getHeight(); y++) {
                pixels[x][y] = new Color(pixmap.getPixel(x, y));
            }
        }

        //final float[] kernel = {0.06136f, 0.24477f, 0.38774f, 0.24477f, 0.06136f};
        final float[] kernel = {0.035822f, 0.05879f, 0.086425f, 0.113806f, 0.13424f, 0.141836f, 0.13424f, 0.113806f, 0.086425f, 0.05879f, 0.035822f};
        final int kw = kernel.length / 2;
        Color tmp;

        //horizontal blur
        for(int x = 0; x < pixmap.getWidth(); x++) {
            for(int y = 0; y < pixmap.getHeight(); y++) {
                Color c = new Color();

                for(int k = -kw; k < kw; k++) {
                    tmp = pixels[MathUtils.clamp(x + k, 0, pixmap.getWidth()-1)][y];
                    c.r += tmp.r * kernel[k+kw];
                    c.g += tmp.g * kernel[k+kw];
                    c.b += tmp.b * kernel[k+kw];
                }

                horiPixels[x][y] = c;
            }
        }

        //vertical blur
        for(int x = 0; x < pixmap.getWidth(); x++) {
            for(int y = 0; y < pixmap.getHeight(); y++) {
                Color c = new Color();

                for(int k = -kw; k < kw; k++) {
                    tmp = horiPixels[x][MathUtils.clamp(y + k, 0, pixmap.getHeight()-1)];
                    c.r += tmp.r * kernel[k+kw];
                    c.g += tmp.g * kernel[k+kw];
                    c.b += tmp.b * kernel[k+kw];
                }

                c.a = 1.0f;
                pixmap.drawPixel(x, y, Color.rgba8888(c));
            }
        }

        return new Texture(pixmap);
    }

    /*private static Color blurredColor(int x, int y, Color[][] pixels) {
        float[][] kernel = {
                {0.005084f, 0.009377f, 0.013539f, 0.015302f, 0.013539f, 0.009377f, 0.005084f},
                {0.009377f, 0.017296f, 0.024972f, 0.028224f, 0.024972f, 0.017296f, 0.009377f},
                {0.013539f, 0.024972f, 0.036054f, 0.040749f, 0.036054f, 0.024972f, 0.013539f},
                {0.015302f, 0.028224f, 0.040749f, 0.046056f, 0.040749f, 0.028224f, 0.015302f},
                {0.013539f, 0.024972f, 0.036054f, 0.040749f, 0.036054f, 0.024972f, 0.013539f},
                {0.009377f, 0.017296f, 0.024972f, 0.028224f, 0.024972f, 0.017296f, 0.009377f},
                {0.005084f, 0.009377f, 0.013539f, 0.015302f, 0.013539f, 0.009377f, 0.005084f}};


        float r = 0, g = 0, b = 0;

        for(int kx = 0; kx < 7; kx++) {
            for(int ky = 0; ky < 7; ky++) {
                r += pixels[x + kx-3][y + ky-3].r * kernel[kx][ky];
                g += pixels[x + kx-3][y + ky-3].g * kernel[kx][ky];
                b += pixels[x + kx-3][y + ky-3].b * kernel[kx][ky];
            }
        }

        return new Color(r, g, b, 1);
    }*/

    public static Pixmap getPixmapFromFramebuffer(FrameBuffer frameBuffer) {
        Texture texture = frameBuffer.getColorBufferTexture();
        frameBuffer.bind();

        byte[] pixelData = ScreenUtils.getFrameBufferPixels(0, 0, texture.getWidth(), texture.getHeight(), false);
        Pixmap pixmap = new Pixmap(texture.getWidth(), texture.getHeight(), frameBuffer.getColorBufferTexture().getTextureData().getFormat());
        ByteBuffer pixels = pixmap.getPixels();
        pixels.clear();
        pixels.put(pixelData);
        pixels.position(0);

        FrameBuffer.unbind();

        return pixmap;
    }

    //TODO: implement
    public static void setMipMapFilter(Texture texture){    }

    public static void setLinearFilter(Texture texture){
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    public static void setNearestFilter(Texture texture){
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
    }

}
