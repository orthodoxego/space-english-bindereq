package com.bindereq.game.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;

public class Font {

    FreeTypeFontGenerator manrope_bold;
    FreeTypeFontGenerator manrope_medium;
    GlyphLayout glyphLayout;

    public static BitmapFont manrope_medium_14px;

    public Font() {
        manrope_bold = new FreeTypeFontGenerator(Gdx.files.internal("font/manrope-bold.ttf"));
        manrope_medium = new FreeTypeFontGenerator(Gdx.files.internal("font/manrope-medium.ttf"));

        glyphLayout = new GlyphLayout();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = Setup.FONT_CHARS;
        parameter.flip = true;
        parameter.kerning = true;
        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 1.6f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("224570");
        parameter.color = Color.valueOf("EEFF00");

        manrope_medium_14px = manrope_medium.generateFont(parameter);

        manrope_bold.dispose();
        manrope_medium.dispose();

    }

    public BitmapFont getManropeMedium14px() {
        return manrope_medium_14px;
    }

    public void dispose() {
        manrope_medium_14px.dispose();
    }


}
