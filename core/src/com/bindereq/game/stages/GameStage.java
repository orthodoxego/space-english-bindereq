package com.bindereq.game.stages;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.actors.Brain;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.view.GameScreen;


public class GameStage extends StageParent {

    Brain brain;

    public GameStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, Textures textures) {
        super(gameScreen, setup, viewport, camera, textures);

        Brain brain = new Brain(textures.getBrains(), GdxViewport.WORLD_WIDTH / 2 - 64, GdxViewport.WORLD_HEIGHT - 192, 128, 128, 0);
        addActor(brain);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

}
