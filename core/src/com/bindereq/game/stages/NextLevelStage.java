package com.bindereq.game.stages;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.actors.TextActor;
import com.bindereq.game.font.Font;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.view.GameScreen;

public class NextLevelStage extends StageParent {
    TextActor textActor;

    public NextLevelStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, Textures textures, int numberLevel, BitmapFont bitmapFont, GlyphLayout glyphLayout) {
        super(gameScreen, setup, viewport, camera, textures);
        String out = "" + numberLevel;
        if (numberLevel < 10) out = "0" + out;

        textActor = new TextActor(this,0, 0, "LEVEL " + out, bitmapFont, glyphLayout, "#FF9800");
        addActor(textActor);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void end() {
        gameScreen.setGameStage();
    }
}
