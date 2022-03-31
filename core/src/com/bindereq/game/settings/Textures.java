package com.bindereq.game.settings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {

    private TextureRegion[] brains;
    private TextureRegion[] circles;

    private TextureRegion rocket;
    private TextureRegion background;

    public Textures() {
        Texture load;

        load = new Texture("png/brains.png");
        brains = new TextureRegion[4];
        brains[0] = getTextureRegionFromMap(0, 0, 128, 128, false, true, load);
        brains[1] = getTextureRegionFromMap(128, 0, 128, 128, false, true, load);
        brains[2] = getTextureRegionFromMap(0, 128, 128, 128, false, true, load);
        brains[3] = getTextureRegionFromMap(128, 128, 128, 128, false, true, load);

        load = new Texture("png/background.png");
        background = getTextureRegionFromMap(0, 0, 480, 800, false, true, load);

        load = new Texture("png/map01.png");
        rocket = getTextureRegionFromMap(0, 64, 64, 64, false, true, load);

        circles = new TextureRegion[4];
        circles[0] = getTextureRegionFromMap(0, 0, 64, 64, false, true, load);
        circles[1] = getTextureRegionFromMap(64, 0, 64, 64, false, true, load);
        circles[2] = getTextureRegionFromMap(128, 0, 64, 64, false, true, load);
        circles[3] = getTextureRegionFromMap(196, 0, 64, 64, false, true, load);

    }

    public TextureRegion[] getBrains() {
        return brains;
    }

    public TextureRegion getRocket() {
        return rocket;
    }

    public TextureRegion getBackground() {
        return background;
    }

    public TextureRegion[] getCircles() {
        return circles;
    }

    private TextureRegion getTextureRegionFromMap(int x, int y, int width, int height, boolean flipX, boolean flipY, Texture texture) {
        TextureRegion tmp = new TextureRegion(texture, x, y, width, height);
        tmp.flip(flipX, flipY);
        return tmp;
    }
}
