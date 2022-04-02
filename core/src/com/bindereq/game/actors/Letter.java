package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.font.Font;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;

public class Letter extends Actor {

    Model model;
    float speedX, speedY;
    int number, widthChr, chr_pos_X;
    public boolean enabled = true;
    TextureRegion letter;
    String chr;
    boolean isReal;
    Font font;

    public Letter(String chr, boolean isReal, Font font, Model model, Textures textures, float x, float y, int number) {
        setName("Letter " + number);
        this.chr = chr;
        this.isReal = isReal;
        this.font = font;
        this.model = model;
        // this.back = textures.getCircles()[0];
        this.letter = textures.getCircles()[0];
        this.number = number;
        GlyphLayout gl = font.getGlyphLayout();
        gl.setText(font.getManropeBold22px(), chr);
        widthChr = (int) gl.width;


        setX(x);
        setY(y);
        setWidth(letter.getRegionWidth() * 1.5f);
        setHeight(letter.getRegionHeight() * 1.5f);
        setOrigin(1, 1);
        setRotation(0);
        setScale(1f, 1f);

        chr_pos_X = (int) ((getWidth() - widthChr) / 2);

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX() + getSpeedX() * delta);
        setY(getY() + (getSpeedY() + model.getSpeed()) * delta);

        if (getX() > GdxViewport.WORLD_WIDTH - letter.getRegionWidth()) {
            setSpeedX(-getSpeedX());
            setX(GdxViewport.WORLD_WIDTH - letter.getRegionWidth());
        } else if (getX() < 0) {
            setSpeedX(-getSpeedX());
            setX(0);
        }

        if (getY() > GdxViewport.WORLD_HEIGHT) enabled = false;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        font.getManropeBold22px().setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        font.getManropeBold22px().draw(batch, chr, getX() + chr_pos_X + Setup.shadow_x / 2, getY() + Setup.shadow_y / 2);

        font.getManropeBold22px().setColor(1, 1, 1, 1);
        font.getManropeBold22px().draw(batch, chr, getX() + chr_pos_X, getY());

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
