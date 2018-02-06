package dev.thomaslienbacher.elevatorfall.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import dev.thomaslienbacher.elevatorfall.Game;

/**
 * @author Thomas Lienbacher
 */
public class Mouse {
	private static Vector2 mousePosition = new Vector2();

	public static void update(OrthographicCamera cam){
		Vector3 vec = new Vector3(Gdx.input.getX(), Gdx.input.getY(), cam.position.z);
		cam.unproject(vec);
		mousePosition = new Vector2(vec.x, vec.y);
	}

	public static float getWorldX(){
		return Mouse.mousePosition.x;
	}

	public static float getWorldY(){
		return Mouse.mousePosition.y;
	}
	
	public static float getScreenX(){
		return (float)Gdx.input.getX() / (float)Gdx.graphics.getWidth() * Game.WIDTH;
	}

	public static float getScreenY(){
		return -((float)Gdx.input.getY() / (float)Gdx.graphics.getHeight() * Game.HEIGHT) + Game.HEIGHT;
	}

	public static Vector2 getScreenVector(){
		return new Vector2(getScreenX(), getScreenY());
	}

	public static Vector2 getWorldVector(){
		return new Vector2(getWorldX(), getWorldY());
	}

	public static boolean isClicked(){
		return Gdx.input.isTouched();
	}

	public static boolean justClicked(){
		return Gdx.input.justTouched();
	}


}
