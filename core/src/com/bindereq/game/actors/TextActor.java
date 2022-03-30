package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.settings.GdxViewport;

public class TextActor extends Actor {

    float x, y, width, height, scaleX = 1.0f, scaleY = 1.0f;
    BitmapFont font;
    String textLine, color;
    int widthText, heightText;
    int[] timeAlpha = new int[] {1, 3, 5};
    float time = 0.0f, alpha = 0.0f;


    public TextActor(float x, float y, String textLine, BitmapFont bitmapFont, GlyphLayout glyphLayout, String color) {
        this.x = x;
        this.y = y;
        this.textLine = textLine;
        this.color = color;
        font = bitmapFont;
        glyphLayout.setText(font, textLine);
        widthText = (int) glyphLayout.width;
        heightText = (int) glyphLayout.height;
        if (this.x == 0 && this.y == 0) {
            this.x = (int) (GdxViewport.WORLD_WIDTH * GdxViewport.ratio - widthText) / 2;
            this.y = (int) (GdxViewport.WORLD_HEIGHT * GdxViewport.ratio - heightText) / 2;
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        time += delta;
        if (time < timeAlpha[0]) {
            alpha += 0.1f;
        } else if (time < timeAlpha[1]) {

        } else if (time < timeAlpha[2]) {
            alpha -= 0.1f;

        }

        if (alpha > 1.0f) alpha = 1.0f;
        else if (alpha < 0.0f) alpha = 0.0f;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, 1);
        font.setColor(Color.valueOf(color));
        font.draw(batch, textLine, getX(), getY());
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }
}
