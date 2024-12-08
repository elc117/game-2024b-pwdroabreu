package com.terminalroot.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class QuizScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;

    public QuizScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont(); // Fonte padrão para testes
    }

    @Override
    public void show() {
        System.out.println("Iniciando Quiz!");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1); // Fundo preto
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        font.draw(batch, "Bem-vindo ao Quiz!", 100, 200); // Mensagem de teste
        batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
