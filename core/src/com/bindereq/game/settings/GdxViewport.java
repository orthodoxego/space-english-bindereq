package com.bindereq.game.settings;

public class GdxViewport {

    public static final float WORLD_WIDTH = 1920f;
    public static final float WORLD_HEIGHT = 1080f;
    public static float TOP;
    public static float BOTTOM;
    public static float HEIGHT;

    public static void resize(int width, int height) {
        float ratio = (float) height / width;
        float viewportHeight = WORLD_WIDTH * ratio;

        BOTTOM = (WORLD_HEIGHT - viewportHeight) / 2;
        TOP = BOTTOM + viewportHeight;
        HEIGHT = TOP - BOTTOM;
    }
}