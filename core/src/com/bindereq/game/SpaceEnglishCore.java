package com.bindereq.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.view.GameScreen;

public class SpaceEnglishCore extends ApplicationAdapter {

	private OrthographicCamera camera;
	private Viewport viewport;
	private AssetManager manager;

	GameScreen gameScreen;
	Setup setup;

	// private SimpleStage stage;

	public static SpaceEnglishCore self() {
		/** Возвращает главный класс игры из любого места. **/
		return (SpaceEnglishCore) Gdx.app.getApplicationListener();
	}

	public static void log(String nameClass, String msg) {
		Gdx.app.log(Setup.APP_ID, nameClass + ": " + msg);
	}

	public static void log(String msg) {
		Gdx.app.log(Setup.APP_ID, "NO CLASS: " + msg);
	}

	@Override
	public void create () {
		camera = new OrthographicCamera();
		viewport = new FillViewport(GdxViewport.WORLD_WIDTH, GdxViewport.WORLD_HEIGHT, camera);
		manager = new AssetManager();

		setup = new Setup();

		gameScreen = new GameScreen(this, setup, viewport, camera, manager);

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		gameScreen.render(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		viewport.update(width, height);
		GdxViewport.resize(width, height);
	}

	@Override
	public void dispose () {
	}
}
