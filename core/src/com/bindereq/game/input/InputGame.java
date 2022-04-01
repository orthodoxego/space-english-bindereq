package com.bindereq.game.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.settings.Const;
import com.bindereq.game.stages.GameStage;

public class InputGame implements InputProcessor {

    GameStage gameStage;

    public InputGame(GameStage gameStage) {
        this.gameStage = gameStage;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) gameStage.press_key_move_brain(Const.KEY.FIRE);
        else if (keycode == Input.Keys.LEFT) gameStage.press_key_move_brain(Const.KEY.LEFT);
        else if (keycode == Input.Keys.RIGHT) gameStage.press_key_move_brain(Const.KEY.RIGHT);
        else if (keycode == Input.Keys.UP) gameStage.press_key_move_brain(Const.KEY.UP);
        else if (keycode == Input.Keys.DOWN) gameStage.press_key_move_brain(Const.KEY.DOWN);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (screenY > Gdx.graphics.getHeight() * 0.75f)
            gameStage.press_mouse(screenX, screenY);
        else
            gameStage.press_key_move_brain(Const.KEY.FIRE);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
