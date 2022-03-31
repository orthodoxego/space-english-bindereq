package com.bindereq.game.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.bindereq.game.SpaceEnglishCore;
import com.bindereq.game.stages.GameStage;

public class InputGame implements InputProcessor {

    Stage gameStage;

    public InputGame(Stage gameStage) {
        this.gameStage = gameStage;
    }

    @Override
    public boolean keyDown(int keycode) {
        SpaceEnglishCore.log(keycode + "");
        if (keycode == )
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
