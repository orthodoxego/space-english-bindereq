package com.bindereq.game.font;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;

public class Font {

    Setup setup;

    FreeTypeFontGenerator comfortaa_b;
    FreeTypeFontGenerator russo_one;
    FreeTypeFontGenerator oswaldm;
    GlyphLayout glyphLayout;

    public static BitmapFont fontInfoLine;
    public static BitmapFont bigTicker;
    public static BitmapFont littleTicker;
    public static BitmapFont fontScore;
    public static BitmapFont advertFont;
    public static BitmapFont nickNameFont;
    public static BitmapFont recordScoreFont;

    public Font(Setup setup) {
        comfortaa_b = new FreeTypeFontGenerator(Gdx.files.internal("fonts/comfortaa-b.ttf"));
        russo_one = new FreeTypeFontGenerator(Gdx.files.internal("fonts/russo-one.ttf"));
        oswaldm = new FreeTypeFontGenerator(Gdx.files.internal("fonts/oswaldm.ttf"));

        glyphLayout = new GlyphLayout();

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.characters = Setup.FONT_CHARS;
        parameter.flip = true;
        parameter.kerning = true;
        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 1.6f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("224570");
        parameter.color = Color.valueOf("EEFF00");

        fontInfoLine = comfortaa_b.generateFont(parameter);

        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 1.2f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("373737");
        parameter.color = Color.valueOf("ffffff");
        bigTicker = russo_one.generateFont(parameter);

        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 2f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("646464");
        parameter.color = Color.valueOf("e852ca");
        littleTicker = russo_one.generateFont(parameter);

        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 1.5f);
        parameter.borderWidth = 1;
        parameter.borderColor = Color.valueOf("646464");
        parameter.color = Color.valueOf("ffffff");
        fontScore = russo_one.generateFont(parameter);

        parameter.size = (int) (GdxViewport.FIXED_BLOCK / 2.1f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("000000");
        parameter.color = Color.valueOf("FFFFFF");
        advertFont = russo_one.generateFont(parameter);

        // Шрифт для никнейма
        parameter.size = (int) (GdxViewport.FIXED_BLOCK * 1.2f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("111111");
        parameter.color = Color.valueOf("5bda5e");
        nickNameFont = russo_one.generateFont(parameter);

        // Шрифт для рекордов
        parameter.size = (int) (GdxViewport.FIXED_BLOCK * 0.55f);
        parameter.borderWidth = 0;
        parameter.borderColor = Color.valueOf("111111");
        // parameter.spaceX = 0;
        // parameter.mono = true;
        parameter.color = Color.valueOf("FFFFFF");
        recordScoreFont = oswaldm.generateFont(parameter);


        comfortaa_b.dispose();
        russo_one.dispose();
    }

    public BitmapFont getAdvertFont() {
        return advertFont;
    }

    public BitmapFont getFontInfoLine() {
        return fontInfoLine;
    }

    public BitmapFont getBigTicker() {
        return bigTicker;
    }

    public BitmapFont getLittleTicker() {
        return littleTicker;
    }

    public BitmapFont getFontScore() {
        return fontScore;
    }

    public BitmapFont getNickNameFont() {
        return nickNameFont;
    }

    public BitmapFont getRecordScoreFont() {
        return recordScoreFont;
    }

    public GlyphLayout getGlyphLayout() {
        return glyphLayout;
    }

    public void dispose() {

    }


}
