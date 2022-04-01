package com.bindereq.game.gamemodel;

import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.Setup;

public class Model {

    private class Task {
        int number = 0;
        public String[] taskWord;
        public String[] taskRus;
        public String[] taskChars;

        public String getTaskWord() {
            return taskWord[number];
        }

        public String getTaskChars() {
            return taskChars[number];
        }

        public void nextTask() {
            number += 1;
            if (number >= taskWord.length) number = 0;
        }
    }

    Setup setup;
    int speed;
    float increase_speed;
    Task task;

    public Model(Setup setup) {
        this.setup = setup;
        task = new Task();
        resetSpeed();
        setLevel(setup.getLevel());
    }

    public void setLevel(int level) {
        switch (level) {
            case 1:
                task.number = 0;
                task.taskWord = new String[] {"HOUSE", "TABLE", "CHAIR"};
                task.taskRus = new String[] {"ДОМ", "СТОЛ", "СТУЛ"};
                task.taskChars = new String[] {"HOUSE",
                                               "TOEABPRLMDE",
                                               "BCDEFHGFADIR"};
                break;
        }

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
