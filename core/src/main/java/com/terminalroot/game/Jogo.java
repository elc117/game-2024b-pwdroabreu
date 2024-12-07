package com.terminalroot.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class Jogo extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image, dino;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("fundojogo1.png");
        dino = new Texture("dino1.png");
    }

    @Override
    public void render() {
        ScreenUtils.clear(1, 0, 0, 1);
        batch.begin();
        batch.draw(image, 0, 0);
        batch.draw(dino, 0, 0);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }
}
