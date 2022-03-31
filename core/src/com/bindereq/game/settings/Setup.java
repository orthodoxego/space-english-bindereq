package com.bindereq.game.settings;

public class Setup {

    public static final String APP_ID = "SPACE ENGLISH";
    public static final String FONT_CHARS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяabcdefghijklmnopqrstuvwxyzАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    int defaultSpeed = 100;
    int default_move_brain = 8;
    float decrement_move_brain = 15f;
    public static float speed_rocket = 400.0f;

    public int getDefaultSpeed() {
        return defaultSpeed;
    }

    public void setDefaultSpeed(int defaultSpeed) {
        this.defaultSpeed = defaultSpeed;
    }

    public int getDefault_move_brain() {
        return default_move_brain;
    }

    public void setDefault_move_brain(int default_move_brain) {
        this.default_move_brain = default_move_brain;
    }

    public float getDecrement_move_brain() {
        return decrement_move_brain;
    }

    public void setDecrement_move_brain(float decrement_move_brain) {
        this.decrement_move_brain = decrement_move_brain;
    }
}
