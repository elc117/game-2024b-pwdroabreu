package com.terminalroot.game;


import com.terminalroot.game.Dino;
import com.terminalroot.game.InteractiveObjectManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;



public class GameRenderer {
    private Texture background;

    public GameRenderer() {
        this.background = new Texture("fundojogo1.png");
    }

    public void render(SpriteBatch batch, Dino dino, InteractiveObjectManager objectManager) {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(dino.getSprite(), dino.getX(), dino.getY());
        for (InteractiveObject obj : objectManager.getObjects()) {
            obj.draw(batch);
        }
        batch.end();
    }

    public void dispose() {
        background.dispose();
    }
}
