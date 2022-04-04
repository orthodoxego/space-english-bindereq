package com.bindereq.game.gamemodel;

import com.bindereq.game.settings.Setup;

public class Model {

    private class Task {
        int number = 0;                    // Подзадание уровня (0-2, например): ДОМ, КВАРТИРА, СТОЛ
        int numCurrentChar = 0;            // Номер текущего символа,
                                           // который должен вылететь на экран
        private int taskCountChars;        // Сколько букв из строки символов должно
                                           // летать на экране одновременно
        public String[] taskEngWord;          // Английское слово-задание
        public String screenStarsWord;   // Слово для отображения в виде звёздочек
        public String[] taskRusWord;           // Русское слово-задание
        public String[] taskChars;         // Какие символы должны вылетать

        public String getCurrentChar() {
            String ret = "" + getTaskChars().charAt(numCurrentChar);
            numCurrentChar += 1;
            if (numCurrentChar >= getTaskChars().length()) numCurrentChar = 0;
            return ret;
        }

        // Установит текущее задание и создаст слово-прокладку
        public void setEngWord(String[] t) {
            taskEngWord = t;
            screenStarsWord = new String[t.length];
            for (int i = 0; i < t.length; i++) {
                screenStarsWord[i] = getBlankChars(t[i]);
            }
        }

        public void setStarsWord(String word) {
            for (int i = 0; i < word.length(); i++)
                screenStarsWord[i] = word.charAt(i) + "";
        }

        // Вернёт строку с символами-звёдочками HOUSE -> *****
        private String getBlankChars(String s) {
            String ret = "";
            for (int i = 0; i < s.length(); i++) {
                ret += "*";
            }
            return ret;
        }

        public String getScreenStarsWord() {
            return screenStarsWord[number];
        }

        // Вернёт английское слово-задание
        public String getEngWord() {
            return taskEngWord[number];
        }

        public String getRusWord() {
            return taskRusWord[number];
        }

        // Вернёт строку символов для вылета на экране
        public String getTaskChars() {
            return taskChars[number];
        }

        // Увеличит номер слова в задании (например, когда первое слово угадано)
        public void nextTask() {
            number += 1;
            if (number >= taskEngWord.length) number = 0;
        }

        // Вернёт количество букв, сколько должно быть одновременно на экране
        public int getTaskCountChars() {
            return taskCountChars;
        }

        // Устанавливает количество букв, видимых на экране
        public void setTaskCountChars(int taskCountChars) {
            // if (taskCountChars > taskWord[number].length()) taskCountChars = 0;
            // else if (taskCountChars < 0) taskCountChars = taskWord[number].length();
            this.taskCountChars = taskCountChars;
        }
    }

    Setup setup;
    int speed;
    float increase_speed;
    Task task;

    public Model(Setup setup) {
        this.setup = setup;
        task = new Task();
        reset();
    }

    // Рестарт уровня
    // Контролировать setup.level - это номер уровня
    public void reset() {
        resetSpeed();
        setLevel(setup.getLevel());
    }

    // Установка уровня
    public void setLevel(int level) {
        switch (level) {
            case 1:
                task.number = 0;
                task.setEngWord(new String[] {"HOUSE", "TABLE", "CHAIR"});
                task.taskRusWord = new String[] {"ДОМ", "СТОЛ", "СТУЛ"};
                task.taskChars = new String[] {"HOUSE",
                                               "TOEABPRLMDE",
                                               "BCDEFHGFADIR"};
                task.setTaskCountChars(5);
                break;
        }
    }

    public void replaseCharInStarsWord(String s) {
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < getCurrentENGWord().length(); i++) {
            if (s.contains("" + getCurrentENGWord().charAt(i)) &&
                    getScreenStarsWord().charAt(i) == '*') {
                word.append(s);
            } else {
                word.append("*");
            }
        }

        task.setStarsWord(word.toString());
    }

    public boolean isReal(String currentChar) {
        for (int i = 0; i < getCurrentENGWord().length(); i++) {
            if (currentChar.contains("" + getCurrentENGWord().charAt(i)) &&
            getScreenStarsWord().charAt(i) == '*') {
                return true;
            }
        }
        return false;
    }

    // Вернёт текущее английское слово
    public String getCurrentENGWord() {
        return task.getEngWord();
    }

    public String getCurrentRUSWord() {
        return task.getRusWord();
    }

    /** Вернёт текущий символ для вывода на экран
        Автоматически переключится на следующий для
        следующего вызова: будет следующий символ */
    public String getCurrentCharToScreen() {
        return task.getCurrentChar();
    }

    // Вернёт слово-звёздочки, например, HOUSE -> *****
    public String getScreenStarsWord() {
        return task.getScreenStarsWord();
    }

    /** Вернёт, сколько должно быть символов на экране */
    public int getTaskCountChars() {
        return task.getTaskCountChars();
    }

    public void act(float delta) {

        /* Замедление дополнительной скорости скроллинга экрана. */
        increase_speed *= 0.997f;

        if (increase_speed != 0 && Math.abs(increase_speed) <= 3)
            increase_speed = 0;
    }

    /** Установит вертикальную скорость по умолчанию из Setup. */
    public void resetSpeed() {
        speed = setup.getDefaultSpeed();
        increase_speed = 0;
    }

    // Вернёт текущую ДОПОЛНИТЕЛЬНУЮ скорость скроллинга
    public int getSpeed() {
        return (int) (speed + increase_speed);
    }

    // Увеличивает ДОПОЛНИТЕЛЬНУЮ скорость скроллинга при нажатии на кнопку
    public void increase_speed() {
        increase_speed += 15;
        if (increase_speed > Setup.maximum_speed_game_field) increase_speed = Setup.maximum_speed_game_field;
    }

    // Уменьшит скорость скроллинга при нажатии на кнопку
    public void decrease_speed() {
        increase_speed -= 15;
        if (increase_speed < -Setup.minimum_speed_game_field) increase_speed = Setup.minimum_speed_game_field;
    }

    public void set_minimum_speed() {
        increase_speed = Setup.minimum_speed_game_field;
    }
}
