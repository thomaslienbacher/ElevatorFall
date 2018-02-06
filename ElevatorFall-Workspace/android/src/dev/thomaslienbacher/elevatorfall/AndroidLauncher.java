package dev.thomaslienbacher.elevatorfall;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import dev.thomaslienbacher.elevatorfall.Game;

/**
 * @author Thomas Lienbacher
 */
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		config.hideStatusBar = true;
		config.useImmersiveMode = true;
		config.numSamples = 4;

		initialize(new Game(), config);
	}
}
