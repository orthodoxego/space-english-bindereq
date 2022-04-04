package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.stages.StageParent;

public class GreenExplosions extends Explosions {

    public GreenExplosions(StageParent stageParent, Model model, TextureRegion[] moves, float x, float y) {
        super(stageParent, model, moves, x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // super.draw(batch, parentAlpha);

        batch.setColor(0, 0.24f, 0.12f, 0.4f);
        // batch.draw(back, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());
        batch.draw(moves[frame], getX() + Setup.shadow_x, getY() + Setup.shadow_y, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        batch.setColor(0, 1, 0, 1);
        // batch.draw(back, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.draw(moves[frame], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
