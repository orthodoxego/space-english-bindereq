package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.stages.StageParent;

public class Explosions extends Actor {

    StageParent stageParent;
    Model model;
    float speedX, speedY, time;
    int frame;
    public boolean enabled = true;
    TextureRegion[] moves;

    public Explosions(StageParent stageParent, Model model, TextureRegion[] moves, float x, float y) {
        this.stageParent = stageParent;
        setName("Explosions");
        this.model = model;
        // this.back = textures.getCircles()[0];
        this.moves = moves;
        this.frame = 0;

        setX(x);
        setY(y);
        setWidth(moves[0].getRegionWidth() * 4);
        setHeight(moves[0].getRegionHeight() * 4);
        setOrigin(1, 1);
        setRotation(0);
        setScale(1f, 1f);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (time > 0.08f) {
            frame += 1;
            time = 0;
        }
        if (frame >= moves.length) {
            frame = moves.length - 1;
            enabled = false;
            stageParent.deleteExplosions();
        }

        setX(getX() + getSpeedX() * delta);
        setY(getY() + (getSpeedY() + model.getSpeed()) * delta);

        if (getX() > GdxViewport.WORLD_WIDTH - moves[0].getRegionWidth()) {
            setSpeedX(-getSpeedX());
            setX(GdxViewport.WORLD_WIDTH - moves[0].getRegionWidth());
        } else if (getX() < 0) {
            setSpeedX(-getSpeedX());
            setX(0);
        }

        if (getY() > GdxViewport.WORLD_HEIGHT) enabled = false;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(0, 0.24f, 0.12f, 0.4f);
        // batch.draw(back, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());
        batch.draw(moves[frame], getX() - getWidth() / 2 + Setup.shadow_x, getY() - getHeight() / 2 + Setup.shadow_y, getOriginX(), getOriginY(), getWidth() * 2, getHeight() * 2, getScaleX(), getScaleY(), getRotation());

        batch.setColor(0.8f, 0.9f, 0.3f, 1f);
        // batch.draw(moves[moves.length - 1 - frame], getX() - getWidth() / 2, getY() - getHeight() / 2, getOriginX(), getOriginY(), getWidth() * 2, getHeight() * 2, getScaleX(), getScaleY(), getRotation());
        batch.draw(moves[frame], getX() - getWidth() / 2, getY() - getHeight() / 2, getOriginX(), getOriginY(), getWidth() * 2, getHeight() * 2, getScaleX(), getScaleY(), getRotation());

        batch.setColor(1, 1, 1, 1);
        // batch.draw(back, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        batch.draw(moves[frame], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());


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
