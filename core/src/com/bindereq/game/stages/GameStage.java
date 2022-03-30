package com.bindereq.game.stages;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.view.GameScreen;

public class GameStage extends StageParent {

    public GameStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera) {
        super(gameScreen, setup, viewport, camera);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
