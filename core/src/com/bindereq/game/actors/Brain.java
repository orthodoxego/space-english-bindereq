package com.bindereq.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;

public class Brain extends Actor {

    public class FuelBrain {
        Textures textures;
        Model model;
        int fuel, one_fuel = 10000 / 9;
        TextureRegion greenSquare, redSquare, boxFuel;
        int x_boxFuel, y_boxFuel, y_box_end_Fuel;

        public FuelBrain(Textures textures, Model model) {
            this.textures = textures;
            this.model = model;
            fuel = getDefaultFuel();
            greenSquare = textures.getGreenSquare();
            redSquare = textures.getRedSquare();
            boxFuel = textures.getBoxFuel();
            x_boxFuel = (int) (GdxViewport.WORLD_WIDTH - 72);
            y_boxFuel = (int) (GdxViewport.WORLD_HEIGHT / 2 - 256);
            y_box_end_Fuel = y_boxFuel + 224;

        }

        public void act(float delta) {
            fuel -= model.getSpeed() * delta;
            if (fuel < 0) fuel = 0;

        }

        public void draw(Batch batch, float parentAlpha) {
            batch.draw(boxFuel, x_boxFuel, y_boxFuel + 1, 0, 0, 32, 256, 1, 1, 0);

            for (int i = 0; i < fuel / one_fuel; i++) {
                if (i < 2) {
                    batch.draw(redSquare, x_boxFuel, y_box_end_Fuel - i * 32,
                            0, 0, 32, 32, 1, 1, 0);
                } else {
                    batch.draw(greenSquare, x_boxFuel, y_box_end_Fuel - i * 32,
                        0, 0, 32, 32, 1, 1, 0);
                }
            }

        }

        private int getDefaultFuel() {
            return 10000;
        }

        private void addFuel() {
            fuel += 2000;
            if (fuel > 10000) {
                fuel = 10000;
            }
        }
    }

    TextureRegion[] textureRegion;
    int levelBrain;
    public float move;
    Setup setup;
    FuelBrain fuelBrain;

    public Brain(Setup setup, Model model, Textures textures, float x, float y, float width, float height, int levelBrain) {

        this.setup = setup;
        setName("Brain");
        this.textureRegion = textures.getBrains();
        fuelBrain = new FuelBrain(textures, model);

        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setOrigin(0, 0);
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
        fuelBrain.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        batch.setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        batch.draw(textureRegion[levelBrain], getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());

        batch.setColor(1, 1, 1, 1);
        batch.draw(textureRegion[levelBrain], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        fuelBrain.draw(batch, parentAlpha);

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

    public void addFuel() {
        fuelBrain.addFuel();
    }
}
