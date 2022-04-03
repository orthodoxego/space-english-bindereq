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
    public static BitmapFont manrope_bold_22px;

    public Font() {
        manrope_bold = new FreeTypeFontGenerator(Gdx.files.internal("font/manrope-bold.ttf"));
        manrope_medium = new FreeTypeFontGenerator(Gdx.files.internal("font/manrope-medium.ttf"));

        glyphLayout = new GlyphLayout();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = Setup.FONT_CHARS;
        parameter.flip = true;
        parameter.kerning = true;
        parameter.size = (int) (GdxViewport.FIXED_BLOCK * 1.5f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("224570");
        parameter.color = Color.valueOf("EEFF00");

        manrope_medium_14px = manrope_medium.generateFont(parameter);

        parameter.characters = Setup.FONT_CHARS;
        parameter.flip = true;
        parameter.kerning = true;
        parameter.size = (int) (GdxViewport.FIXED_BLOCK * 2.1f);
        parameter.borderWidth = 1;
        parameter.borderColor = Color.valueOf("224570");
        parameter.color = Color.valueOf("FFFFFF");

        manrope_bold_22px = manrope_bold.generateFont(parameter);

        manrope_bold.dispose();
        manrope_medium.dispose();

    }

    public GlyphLayout getGlyphLayout() {
        return this.glyphLayout;
    }

    public BitmapFont getManropeMedium14px() {
        return manrope_medium_14px;
    }

    public BitmapFont getManropeBold22px() {
        return manrope_bold_22px;
    }

    public void dispose() {
        manrope_medium_14px.dispose();
        manrope_bold_22px.dispose();
    }


}
