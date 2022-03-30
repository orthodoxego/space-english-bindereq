package com.bindereq.game.settings;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Textures {

    private TextureRegion[] brains;

    public Textures() {
        Texture load;

        load = new Texture("png/brains.png");
        brains = new TextureRegion[4];
        brains[0] = getTextureRegionFromMap(0, 0, 128, 128, false, true, load);
        brains[1] = getTextureRegionFromMap(128, 0, 128, 128, false, true, load);
        brains[2] = getTextureRegionFromMap(0, 128, 128, 128, false, true, load);
        brains[3] = getTextureRegionFromMap(128, 128, 128, 128, false, true, load);

    }

    public TextureRegion[] getBrains() {
        return brains;
    }

    private TextureRegion getTextureRegionFromMap(int x, int y, int width, int height, boolean flipX, boolean flipY, Texture texture) {
        TextureRegion tmp = new TextureRegion(texture, x, y, width, height);
        tmp.flip(flipX, flipY);
        return tmp;
    }
}
