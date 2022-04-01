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
    public float move;
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
        move = 0;

        this.levelBrain = levelBrain;

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (move < 0) {
            move += setup.getDecrement_move_brain() * delta;
            if (getX() < 0) {
                move = -move;
                setX(0);
            }
        } else if (move > 0) {
            move -= setup.getDecrement_move_brain() * delta;
            if (getX() > GdxViewport.WORLD_WIDTH - textureRegion[0].getRegionWidth()) {
                move = -move;
                setX(GdxViewport.WORLD_WIDTH - textureRegion[0].getRegionWidth());
            }
        }

        setX(getX() + move);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        batch.draw(textureRegion[levelBrain], getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());

        batch.setColor(1, 1, 1, 1);
        batch.draw(textureRegion[levelBrain], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());



    }

    public void move_left() {
        move += -setup.getDefault_move_brain();
        if (move < -setup.getDefault_move_brain() * 2)
            move = -setup.getDefault_move_brain() * 2;
    }

    public void move_right() {
        move += setup.getDefault_move_brain();
        if (move > setup.getDefault_move_brain() * 2)
            move = setup.getDefault_move_brain() * 2;
    }
}
