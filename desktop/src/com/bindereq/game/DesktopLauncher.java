package com.bindereq.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		int WIDTH = 1080;
		int HEIGHT = 1920;
		float SCALE = 2.0f;

		config.setWindowSizeLimits((int) (WIDTH / SCALE),
				(int) (HEIGHT / SCALE),
				(int) (WIDTH / SCALE),
				(int) (HEIGHT / SCALE));

		config.setBackBufferConfig(8, 8, 8, 8, 16, 0, 5);

		config.setForegroundFPS(120);
		new Lwjgl3Application(new SpaceEnglishCore(), config);
	}
}
