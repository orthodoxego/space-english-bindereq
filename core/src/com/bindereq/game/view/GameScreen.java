package com.bindereq.game.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.stages.GameStage;

public class GameScreen implements Screen {

    SpaceEnglishCore spaceEnglishCore;
    Setup setup;
    Viewport viewport;
    OrthographicCamera camera;
    AssetManager manager;
    Stage currentStage;

    public GameScreen(SpaceEnglishCore spaceEnglishCore, Setup setup, Viewport viewport, OrthographicCamera camera, AssetManager manager) {
        this.spaceEnglishCore = spaceEnglishCore;
        this.setup = setup;
        this.viewport = viewport;
        this.camera = camera;
        this.manager = manager;

        setGameStage();
    }

    public void setGameStage() {
        currentStage = new GameStage(this, setup, viewport, camera, manager);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        /** Отрисует текущую сцену Stage. **/
        camera.update();
        ScreenUtils.clear(0, 0, 0, 1);

        currentStage.act(delta);
        currentStage.draw();

        // SpaceEnglishCore.log(this.getClass().toString(), "" + delta);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        GdxViewport.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
