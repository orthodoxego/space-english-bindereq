package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;

public class Brain extends Actor {

    TextureRegion[] textureRegion;
    int levelBrain;
    Setup setup;

    public Brain(Setup setup, TextureRegion[] textureRegion, float x, float y, float width, float height, int levelBrain) {

        this.setup = setup;
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

        batch.setColor(0, 32.0f / 255, 32f / 255, 0.5f);
        batch.draw(textureRegion[levelBrain], getX() + 64 + (int) (Math.random() * 7), getY() + 64 + (int) (Math.random() * 5), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.8f, 0.8f, getRotation());

        batch.setColor(1, 1, 1, 1);
        batch.draw(textureRegion[levelBrain], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());



    }
}
