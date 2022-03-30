package com.bindereq.game.stages;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.view.GameScreen;

public class GameStage extends Stage {

    GameScreen gameScreen;
    Setup setup;
    Viewport viewport;
    OrthographicCamera camera;
    AssetManager manager;

    public GameStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, AssetManager manager) {
        this.gameScreen = gameScreen;
        this.setup = setup;
        this.viewport = viewport;
        this.camera = camera;
        this.manager = manager;
    }


}
