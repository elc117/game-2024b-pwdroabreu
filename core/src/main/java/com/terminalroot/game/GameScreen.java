package com.terminalroot.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
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

public class GameScreen implements Screen {
    private final Main game;
    private final SpriteBatch batch;
    private final Dino dino;
    private final InteractiveObjectManager objectManager;
    private final GameRenderer renderer;
    private final QuizManager quizManager;
    private final Texture lifeSprite;

    public GameScreen(Main game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;


        dino = new Dino(new Texture("dino1.png"), 100, 100, 0.3f);

        objectManager = new InteractiveObjectManager();
        objectManager.loadObjects();

        renderer = new GameRenderer();

        quizManager = new QuizManager(batch, game);

        lifeSprite = new Texture("life.png");
    }

    @Override
    public void render(float delta) {
        if (quizManager.isQuizActive()) {
            quizManager.renderQuiz();
        } else {
            ScreenUtils.clear(1, 0, 0, 1);

            dino.handleInput(delta);
            objectManager.checkInteractions(dino);

            InteractiveObject currentObject = objectManager.getCurrentObject();
            if (currentObject != null && Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.SPACE)) {
                quizManager.startQuiz(currentObject);
            }


            renderer.render(batch, dino, objectManager);


            renderLives();
        }
    }

    private void renderLives() {
        batch.begin();
        float lifeSize = 90;
        float padding = 15;

        for (int i = 0; i < game.getLives(); i++) {
            float x = Gdx.graphics.getWidth() - ((i + 1) * (lifeSize + padding));
            float y = Gdx.graphics.getHeight() - lifeSize - padding;
            batch.draw(lifeSprite, x, y, lifeSize, lifeSize);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        dino.dispose();
        objectManager.dispose();
        renderer.dispose();
        quizManager.dispose();
        lifeSprite.dispose();
    }

    @Override
    public void show() {}
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
}
