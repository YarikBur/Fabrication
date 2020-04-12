package ru.asfick.fabrication.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.asfick.fabrication.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 850;
		config.resizable = false;
		config.title = "Fabrication";
		new LwjglApplication(new Main(), config);
	}
}
