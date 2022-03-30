package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.GdxViewport;

public class Brain extends Actor {

    TextureRegion[] textureRegion;
    int levelBrain;

    public Brain(TextureRegion[] textureRegion, float x, float y, float width, float height, int levelBrain) {
        setName("Brain");
        this.textureRegion = textureRegion;
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setOrigin(1, 1);
        setRotation(0);
        setScale(1, 1);
        this.levelBrain = levelBrain;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(textureRegion[levelBrain], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }
}
