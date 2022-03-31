package com.bindereq.game.gamemodel;

import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.Setup;

public class Model {
    Setup setup;
    int speed;
    float increase_speed;

    public Model(Setup setup) {
        this.setup = setup;

        resetSpeed();
    }

    public void act(float delta) {

        /* Замедление дополнительной скорости скроллинга экрана. */
        increase_speed *= 0.995f;
        if (increase_speed != 0 && Math.abs(increase_speed) <= 3)
            increase_speed = 0;
    }

    /** Установит вертикальную скорость по умолчанию из Setup. */
    public void resetSpeed() {
        speed = setup.getDefaultSpeed();
        increase_speed = 0;
    }

    public int getSpeed() {
        return (int) (speed + increase_speed);
    }

    public void increase_speed() {
        increase_speed += 15;
        if (increase_speed > 150) increase_speed = 150;
    }

    public void decrease_speed() {
        increase_speed -= 15;
        if (increase_speed < -50) increase_speed = -50;
    }
}
