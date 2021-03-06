package dev.thomaslienbacher.elevatorfall.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dev.thomaslienbacher.elevatorfall.Game;

/**
 * @author Thomas Lienbacher
 */
public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.x = -1;
        config.y = 6;

        config.height = Game.HEIGHT;
        config.width = Game.WIDTH;
        config.title = Game.APP_NAME + " Desktop";
        config.samples = 4;
        config.allowSoftwareMode = false;
        config.fullscreen = false;
        config.vSyncEnabled = true;
        config.foregroundFPS = 60;
        config.backgroundFPS = 20;

        config.forceExit = false;

        new LwjglApplication(new Game(), config);
    }
}
