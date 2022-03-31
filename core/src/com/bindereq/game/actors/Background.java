package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.stages.GameStage;

public class Background extends Actor {
    TextureRegion textureRegion;
    Model model;
    float y1, y2, y3;
    int h;

    public Background(TextureRegion textureRegion, Model model) {
        this.textureRegion = textureRegion;
        this.model = model;
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());
        setOrigin(1, 1);
        setRotation(0);
        setScale(2, 2);
        h = textureRegion.getRegionHeight() * 2;
        y1 = 0;
        y2 = -h;
        y3 = y2 - h;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        y1 += model.getSpeed() * delta * 3;
        y2 += model.getSpeed() * delta * 3;
        y3 += model.getSpeed() * delta * 3;

        if (y1 >= GdxViewport.HEIGHT) {
            y1 = y3 - h;
        }
        if (y2 >= GdxViewport.HEIGHT) {
            y2 = y1 - h;
        }
        if (y3 >= GdxViewport.HEIGHT) {
            y3 = y2 - h;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (y1 + h >= 0)
            batch.draw(textureRegion, 0, y1, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        if (y2 + h >= 0)
            batch.draw(textureRegion, 0, y2, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        if (y3 + h >= 0)
            batch.draw(textureRegion, 0, y3, getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
