package com.bindereq.game.stages;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.actors.Background;
import com.bindereq.game.actors.Brain;
import com.bindereq.game.actors.Fire;
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
        brain = new Brain(setup, textures.getBrains(), GdxViewport.WORLD_WIDTH / 2 - 64, GdxViewport.WORLD_HEIGHT - 256, 128, 128, 0);
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

        // Прорисовка ракет
        for (int i = fire.size() - 1; i > -1; i--) {
            fire.elementAt(i).act(delta);
            if (!fire.elementAt(i).enabled) {
                fire.elementAt(i).remove();
                fire.remove(fire.elementAt(i));
            }
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
                Fire f = new Fire(model, textures.getRocket(), brain.getX() + brain.getWidth() / 2 - 16, brain.getY(), fire.size());
                f.setSpeedX(brain.move * 5);
                f.setSpeedY(Setup.speed_rocket);
                fire.add(f);
                addActor(f);
                break;

        }
    }

    public void press_mouse(int screenX, int screenY) {
        if (screenX < (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_left();
        if (screenX > (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL) brain.move_right();
        SpaceEnglishCore.log((screenX) + " " + (brain.getX() + brain.getWidth() / 2) / GdxViewport.RATIO_HORIZONTAL);
    }
}
