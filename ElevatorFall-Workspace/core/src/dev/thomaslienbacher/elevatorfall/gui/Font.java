package dev.thomaslienbacher.elevatorfall.gui;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *	This class is used to render Fonts and manage single Fonts.
 * 
 * @author Thomas Lienbacher
 */
public class Font {
	private BitmapFont bitmapFont;
	
	public Font(BitmapFont bitmapFont){
		this.bitmapFont = bitmapFont;
		this.bitmapFont.setUseIntegerPositions(false);
		this.bitmapFont.getData().markupEnabled = true;
	}

	public void render(SpriteBatch batch, String text, float x, float y){
		bitmapFont.draw(batch, text, x, y);
	}
	
	public void render(SpriteBatch batch, String text, float x, float y, Color color){
		bitmapFont.setColor(color);
		bitmapFont.draw(batch, text, x, y);
		bitmapFont.setColor(Color.WHITE);
	}

	public BitmapFont getBitmapFont() {
		return bitmapFont;
	}

}
