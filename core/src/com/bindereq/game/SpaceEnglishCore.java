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

public class SpaceEnglishCore extends ApplicationAdapter {

	private OrthographicCamera camera;
	private Viewport viewport;
	private AssetManager manager;
	// private SimpleStage stage;

	public static SpaceEnglishCore self() {
		/** Возвращает главный класс игры из любого места. **/
		return (SpaceEnglishCore) Gdx.app.getApplicationListener();
	}

	@Override
	public void create () {

		camera = new OrthographicCamera();
		viewport = new FillViewport(GdxViewport.WORLD_WIDTH, GdxViewport.WORLD_HEIGHT, camera);
		manager = new AssetManager();

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
	}
	
	@Override
	public void dispose () {
	}
}