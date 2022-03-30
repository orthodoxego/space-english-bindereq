package com.bindereq.game.stages;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.view.GameScreen;

public class StageParent extends Stage {

    GameScreen gameScreen;
    Setup setup;
    OrthographicCamera camera;
    Textures textures;

    public StageParent(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, Textures textures) {
        this.gameScreen = gameScreen;
        this.setup = setup;
        this.setViewport(viewport);
        this.camera = camera;
        this.textures = textures;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public void end() {

    }
}