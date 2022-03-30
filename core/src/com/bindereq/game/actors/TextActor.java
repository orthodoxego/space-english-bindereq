package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.view.GameScreen;

public class TextActor extends Actor {

    GameScreen gameScreen;
    float x, y, width, height, scaleX = 1.0f, scaleY = 1.0f;
    BitmapFont font;
    String textLine, color;
    int widthText, heightText;
    int[] timeAlpha = new int[] {1, 2, 4};
    float time = 0.0f, alpha = 0.0f;


    public TextActor(GameScreen gameScreen, float x, float y, String textLine, BitmapFont bitmapFont, GlyphLayout glyphLayout, String color) {
        this.gameScreen = gameScreen;
        this.x = x;
        this.y = y;
        this.textLine = textLine;
        this.color = color;
        font = bitmapFont;

        glyphLayout.setText(font, textLine);

        widthText = (int) glyphLayout.width;
        heightText = (int) glyphLayout.height;
        if (this.x == 0 && this.y == 0) {
            this.x = (GdxViewport.WORLD_WIDTH * GdxViewport.ratio - widthText) / 2;
            this.y = (GdxViewport.WORLD_HEIGHT * GdxViewport.ratio - heightText) / 2;
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        time += delta;

        // Проявление и выцветание шрифта
        if (time < timeAlpha[0]) alpha += 0.03f;
        else if (time < timeAlpha[1]) alpha = 1.0f;
        else if (time < timeAlpha[2]) alpha -= 0.01f;

        if (alpha > 1.0f) alpha = 1.0f;
        else if (alpha < 0.0f) {
            alpha = 0.0f;
            gameScreen.setGameStage();
        }

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        // font.setColor(Color.valueOf(color).r, Color.valueOf(color).g, Color.valueOf(color).b, alpha);
        font.setColor(Color.valueOf(color).r, Color.valueOf(color).g, Color.valueOf(color).b, 1);
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
