package com.bindereq.game.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.actors.Background;
import com.bindereq.game.actors.Brain;
import com.bindereq.game.actors.Explosions;
import com.bindereq.game.actors.Fire;
import com.bindereq.game.actors.Fuel;
import com.bindereq.game.actors.Letter;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.settings.Const;
import com.bindereq.game.settings.GdxViewport;
import com.bindereq.game.settings.Setup;
import com.bindereq.game.settings.Textures;
import com.bindereq.game.view.GameScreen;

import java.util.Vector;


public class GameStage extends StageParent {

    Model model;
    Brain brain;
    Vector<Fire> fire = new Vector<>();
    Vector<Fuel> fuel = new Vector<>();
    Vector<Letter> letter = new Vector<>();
    Vector<Explosions> explosions = new Vector<>();
    int countFrame = 0;
    float fuelFrame = 0;

    public GameStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, Textures textures) {
        super(gameScreen, setup, viewport, camera, textures);
        reset();
    }

    /** Рестарт игры. setup.level увеличить в другом месте. */
    private void reset() {
        model = new Model(setup);
        addActors();
    }

    /**
     * Добавит задний фон и игрока на сцену.
     */
    public void addActors() {
        Background background = new Background(textures.getBackground(), model);
        brain = new Brain(setup, model, textures, GdxViewport.WORLD_WIDTH / 2 - 64, GdxViewport.WORLD_HEIGHT - 256, 128, 128, 0);
        addActor(background);
        addActor(brain);
    }

    /**
     * Ежекадровое обновление.
     */
    @Override
    public void act(float delta) {
        super.act(delta);
        model.act(delta);

        // Увеличение счётчика кадров
        countFrame += 1;
        if (countFrame > 1000) countFrame = 0;

        // Добавление заправочных юнитов
        fuelFrame += delta;
        if (fuelFrame > 3) {
            fuelFrame = 0;
            addFuel();
        }

        // Проверить столкновение снаряда и заправки
        if (fuel.size() > 0 && fire.size() > 0 && countFrame % 3 == 0) checkCollisionFuelAndFire();

        // Каждые 5 кадров удаляются отключенные объекты
        if (countFrame % 10 == 0) {
            if (letter.size() < model.getTaskCountChars()) {
                addLetters();
            }
            deleteDisabledObjects();
        }

        // Проверить столкновение заправки и героя
        if (fuel.size() > 0 && countFrame % 10 == 0) {
            checkCollisionFuelAndPlayer();
        }
    }

    /** Рассчитает и добавит недостающие символы-актёров. */
    private void addLetters() {
        for (int i = letter.size(); i < model.getTaskCountChars(); i++) {
            String currentChar = model.getCurrentCharToScreen();
            boolean isReal = model.getCurrentENGWord().contains(currentChar);

            float y = -((int) (Math.random() * GdxViewport.WORLD_HEIGHT / 10));

            if (letter.size() > 0) {
                y = letter.get(i - 1).getY() - (int) (Math.random() * GdxViewport.WORLD_HEIGHT * 0.1f) - GdxViewport.WORLD_HEIGHT / model.getTaskCountChars();
            }
            if (y > -textures.getCircles()[0].getRegionHeight()) {
                y = -textures.getCircles()[0].getRegionHeight();
            }

            Letter l = new Letter(currentChar,
                    isReal,
                    gameScreen.getFont(),
                    model,
                    textures,
                    (int) (Math.random() * GdxViewport.WORLD_WIDTH) - textures.getCircles()[0].getRegionWidth() - 5,
                    y,
                    letter.size());
            l.setSpeedY(Setup.speed_letter * (int) (Math.random() * 5));
            l.setSpeedX((float) ((Setup.speed_letter / 2) * (Math.random() * 4)));
            addActor(l);
            letter.add(l);
        }
        brain.toFront();
    }

    /** Удалит отключенных актёров. */
    private void deleteDisabledObjects() {
        // Удаление заправочных юнитов
        for (int i = fuel.size() - 1; i > -1; i--) {
            if (!fuel.elementAt(i).enabled) {
                fuel.elementAt(i).remove();
                fuel.remove(fuel.elementAt(i));
            }
        }

        // Удаление символов
        for (int i = letter.size() - 1; i > -1; i--) {
            if (!letter.elementAt(i).enabled) {
                letter.elementAt(i).remove();
                letter.remove(letter.elementAt(i));
            }
        }

        // Удаление ракет
        for (int i = fire.size() - 1; i > -1; i--) {
            if (!fire.elementAt(i).enabled) {
                fire.elementAt(i).remove();
                fire.remove(fire.elementAt(i));
            }
        }

        // Удаление взрывов
        for (int i = explosions.size() - 1; i > -1; i--) {
            if (!explosions.elementAt(i).enabled) {
                explosions.elementAt(i).remove();
                explosions.remove(explosions.elementAt(i));
            }
        }


    }

    /** Проверка столкновения двух актёров по минимальной ширине. */
    private boolean isCollision(Actor a, Actor b) {
        int xa, ya, xb, yb;
        xa = (int) (a.getX() + a.getWidth() / 2);
        ya = (int) (a.getY() + a.getHeight() / 2);
        xb = (int) (b.getX() + b.getWidth() / 2);
        yb = (int) (b.getY() + b.getHeight() / 2);

        int c = (int) Math.sqrt(Math.pow(xa - xb, 2) + Math.pow(ya - yb, 2));
        if (c < Math.min(a.getWidth(), b.getWidth())) {
            return true;
        }
        return false;
    }

    /** Проверка столкновения заправки и ракеты. */
    private boolean checkCollisionFuelAndFire() {
        boolean res = false;
        for (Fuel f: fuel) {
            for (Fire fr: fire) {
                if (isCollision(f, fr)) {
                    fr.enabled = false;
                    f.enabled = false;
                    Explosions e = new Explosions(model,
                            textures.getExplosions(),
                            f.getX() - 32, f.getY() - 32);
                    e.setSpeedX(0);
                    e.setSpeedY(f.getSpeedY());
                    explosions.add(e);
                    addActor(e);
                    brain.toFront();
                    res = true;
                }
            }
        }
        return res;
    }

    /** Проверка столкновения заправки и игрока. */
    private void checkCollisionFuelAndPlayer() {
        for (Fuel f: fuel) {
            if (f.getY() + f.getHeight() > brain.getY()) {
                if (isCollision(f, brain)) {
                    brain.addFuel();
                    f.enabled = false;
                }
            }
        }
    }

    /** Добавит заправку, если заправок меньше 3 и пришло время (см. act()). */
    private void addFuel() {
        if (fuel.size() < 3) {
            Fuel f = new Fuel(model, textures, (float) (Math.random() * GdxViewport.WORLD_WIDTH - 64), 0, fuel.size());
            f.setSpeedX(Setup.speed_fuel_x);
            f.setSpeedY(Setup.speed_fuel_y);
            fuel.add(f);
            addActor(f);
            brain.toFront();
        }
    }

    /** Реакция на клавиши. */
    public void press_key_move_brain(Const.KEY key) {
        switch (key) {
            case LEFT:
                brain.move_left();
                break;
            case RIGHT:
                brain.move_right();
                break;
            case UP:
                model.increase_speed();
                break;
            case DOWN:
                model.decrease_speed();
                break;
            case FIRE:
                Fire f = new Fire(model, textures.getRocket(), brain.getX() + brain.getWidth() / 2 - 16, brain.getY() - 32, fire.size());
                // Скорость ракеты получает ускорение движения мозга
                f.setSpeedX(brain.move * 5);
                // Константа скорости ракеты
                f.setSpeedY(Setup.speed_rocket);
                fire.add(f);
                addActor(f);
                break;

        }
    }

    /** Реакция на мышь. */
    public void press_mouse(int screenX, int screenY) {
        if (screenX < (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_left();
        if (screenX > (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_right();
        // SpaceEnglishCore.log((screenX) + " " + (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL);
    }
}
