package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.stages.StageParent;

public class Fuel extends Actor {

    StageParent stageParent;
    Model model;
    float speedX, speedY;
    int number;
    public boolean enabled = true;
    TextureRegion fuelIco;
    // TextureRegion back;

    public Fuel(StageParent stageParent, Model model, Textures textures, float x, float y, int number) {
        this.stageParent = stageParent;
        setName("Fuel" + number);
        this.model = model;
        // this.back = textures.getCircles()[0];
        this.fuelIco = textures.getFuelIco();
        this.number = number;

        setX(x);
        setY(y);
        setWidth(fuelIco.getRegionWidth());
        setHeight(fuelIco.getRegionHeight());
        setOrigin(1, 1);
        setRotation(0);
        setScale(1f, 1f);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + getSpeedX() * delta);
        setY(getY() + (getSpeedY() + model.getSpeed()) * delta);

        if (getX() > GdxViewport.WORLD_WIDTH - fuelIco.getRegionWidth()) {
            setSpeedX(-getSpeedX());
            setX(GdxViewport.WORLD_WIDTH - fuelIco.getRegionWidth());
        } else if (getX() < 0) {
            setSpeedX(-getSpeedX());
            setX(0);
        }

        if (getY() > GdxViewport.WORLD_HEIGHT) {
            enabled = false;
            stageParent.deleteFuel();
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        // batch.draw(back, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());
        batch.draw(fuelIco, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());

        batch.setColor(1, 1, 1, 1);
        // batch.draw(back, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.draw(fuelIco, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

    }

    public float getSpeedX() {
        return speedX;
    }

    public void setSpeedX(float speedX) {
        if (Math.random() * 100 < 50) speedX *= -1;
        this.speedX = speedX;
    }

    public float getSpeedY() {
        return speedY;
    }

    public void setSpeedY(float speedY) {
        this.speedY = speedY;
    }
}
