package com.terminalroot.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class VictoryScreen implements Screen {
    private final SpriteBatch batch;
    private final Main game;
    private final Texture victoryImage;

    public VictoryScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.victoryImage = new Texture("victory.png");
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(victoryImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.startGame();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        victoryImage.dispose();
    }

    @Override
    public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void show() {}
    @Override public void hide() {}
}
