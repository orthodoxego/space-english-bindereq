package com.bindereq.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.actors.Background;
import com.bindereq.game.actors.Brain;
import com.bindereq.game.actors.Fire;
import com.bindereq.game.actors.Fuel;
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
    int countFrame = 0;
    float fuelFrame = 0;

    public GameStage(GameScreen gameScreen, Setup setup, Viewport viewport, OrthographicCamera camera, Textures textures) {
        super(gameScreen, setup, viewport, camera, textures);
        model = new Model(setup);
        addActors();
    }

    /**
     * Добавит актёров на сцену.
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

        // Прорисовка и удаление ракет
        for (int i = fire.size() - 1; i > -1; i--) {
            fire.elementAt(i).act(delta);
            if (!fire.elementAt(i).enabled) {
                fire.elementAt(i).remove();
                fire.remove(fire.elementAt(i));
            }
        }

        // Добавление заправочных юнитов
        fuelFrame += delta;
        if (fuelFrame > 3) {
            fuelFrame = 0;
            addFuel();
        }
        // Прорисовка и удаление заправочных юнитов
        for (int i = fuel.size() - 1; i > -1; i--) {
            fuel.elementAt(i).act(delta);
            if (!fuel.elementAt(i).enabled) {
                fuel.elementAt(i).remove();
                fuel.remove(fuel.elementAt(i));
            }
        }

        // Проверить столкновение заправки и героя
        if (fuel.size() > 0 && countFrame % 10 == 0) {
            checkCollisionFuelAndPlayer();
        }
    }

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

    public void press_mouse(int screenX, int screenY) {
        if (screenX < (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_left();
        if (screenX > (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_right();
        // SpaceEnglishCore.log((screenX) + " " + (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL);
    }
}
