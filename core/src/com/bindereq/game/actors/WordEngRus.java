package com.bindereq.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.bindereq.game.font.Font;
import com.bindereq.game.gamemodel.Model;
import com.bindereq.game.stages.StageParent;

public class WordEngRus extends Actor {

    StageParent stageParent;
    Model model;
    Font font;
    String eng, rus;
    int heightEng;

    public WordEngRus(StageParent stageParent, Model model, Font font, String eng, String rus, int x, int y) {
        this.stageParent = stageParent;
        this.model = model;
        this.font = font;
        this.eng = eng;
        this.rus = rus;

        setX(x);
        setY(y);
        GlyphLayout gl = font.getGlyphLayout();
        gl.setText(font.getManropeBold22px(), eng);
        heightEng = (int) (gl.height * 1.2f);


    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        font.getManropeBold22px().setColor(1, 1, 1, 1);
        font.getManropeBold22px().draw(batch, eng, getX(), getY());

        font.getManropeBold22px().setColor(1, 1, 1, 1);
        font.getManropeBold22px().draw(batch, rus, getX(), getY() + heightEng);

    }
}
