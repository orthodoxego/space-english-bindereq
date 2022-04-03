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
import com.bindereq.game.stages.StageParent;

public class Letter extends Actor {

    StageParent stageParent;
    Model model;
    float speedX, speedY;
    int number, widthChr, chr_pos_X;
    public boolean enabled = true;
    TextureRegion letter;
    String chr;
    boolean isReal;
    Font font;

    public Letter(StageParent stageParent, String chr, boolean isReal, Font font, Model model, Textures textures, float x, float y, int number) {
        this.stageParent = stageParent;
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
        setWidth(letter.getRegionWidth() * 0.8f);
        setHeight(letter.getRegionHeight() * 0.8f);
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

        if (getY() > GdxViewport.WORLD_HEIGHT) {
            enabled = false;
            stageParent.deleteLetters();
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        // batch.setColor(0, 32.0f / 255, 32f / 255, 0.4f);
        // batch.draw(back, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());
        // batch.draw(letter, getX() + Setup.shadow_x + (int) (Math.random() * 8), getY() + Setup.shadow_y + (int) (Math.random() * 8), getOriginX(), getOriginY(), getWidth(), getHeight(), 0.9f, 0.9f, getRotation());

        // batch.setColor(1, 1, 1, 1);
        // batch.draw(letter, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        font.getManropeBold22px().setColor(0f, 0.12f, 0.12f, 0.4f);
        font.getManropeBold22px().draw(batch, chr, getX() + chr_pos_X + Setup.shadow_x, getY() + Setup.shadow_y);

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
