package com.bindereq.game.gamemodel;

import com.bindereq.game.settings.Setup;

public class Model {
    Setup setup;
    int speed;

    public Model(Setup setup) {
        this.setup = setup;

        resetSpeed();
    }

    /** Установит вертикальную скорость по умолчанию из Setup. */
    public void resetSpeed() {
        speed = setup.getDefaultSpeed();
    }

    public int getSpeed() {
        return speed;
    }
}
