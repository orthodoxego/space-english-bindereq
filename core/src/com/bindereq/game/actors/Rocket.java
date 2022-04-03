package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.stages.StageParent;

import java.util.Set;

public class Rocket extends Actor {

    StageParent stageParent;
    Model model;
    TextureRegion textureRegion;
    float speedX, speedY;
    int number;
    public boolean enabled = true;

    public Rocket(StageParent stageParent, Model model, TextureRegion textureRegion, float x, float y, int number) {
        this.stageParent = stageParent;
        setName("Rocket" + number);
        this.model = model;
        this.textureRegion = textureRegion;
        this.number = number;

        setX(x);
        setY(y);
        setWidth(textureRegion.getRegionWidth());
        setHeight(textureRegion.getRegionHeight());
        setOrigin(1, 1);
        setRotation(0);
        setScale(1.2f, 1.2f);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + getSpeedX() * delta);
        setY(getY() - (getSpeedY() - model.getSpeed()) * delta);

        if (getY() + getHeight() < 0) {
            enabled = false;
            stageParent.deleteRockets();
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        batch.draw(textureRegion, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());

        batch.setColor(1, 1, 1, 1);
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
