package com.terminalroot.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Gdx;

public class MenuScreen implements Screen {
    private final SpriteBatch batch;
    private final Main game;
    private final Texture backgroundImage;

    public MenuScreen(Main game) {
        this.game = game;
        this.batch = new SpriteBatch();
        this.backgroundImage = new Texture("menu_background.png"); // Certifique-se de que a imagem está em "assets"
    }

    @Override
    public void render(float delta) {
        // Limpa a tela e renderiza a imagem de fundo
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();
        batch.draw(backgroundImage, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        // Inicia o jogo ao pressionar ENTER
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            game.startGame();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        backgroundImage.dispose();
    }

    @Override
    public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void show() {}
    @Override public void hide() {}
}
