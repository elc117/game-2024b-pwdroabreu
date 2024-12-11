package com.terminalroot.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;



public class QuizScreen implements Screen {

    private final SpriteBatch batch;
    private final BitmapFont font;


    private final String question;
    private final String[] options;
    private final int correctOption;
    private int selectedOption = -1;
    private String resultMessage = "";

    public QuizScreen() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2);


        question = "Qual é a capital do Brasil?";
        options = new String[]{"1. Brasilia", "2. Londres", "3. Berlim", "4. Madri"};
        correctOption = 1;
    }

    @Override
    public void render(float delta) {
        System.out.println("Renderizando QuizScreen...");
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        handleInput();


        batch.begin();


        font.draw(batch, question, 50, Gdx.graphics.getHeight() - 50);


        for (int i = 0; i < options.length; i++) {
            String optionText = options[i];
            if (i == selectedOption) {
                optionText = "> " + optionText;
            }
            font.draw(batch, optionText, 50, Gdx.graphics.getHeight() - 100 - (i * 50));
        }


        if (!resultMessage.isEmpty()) {
            font.draw(batch, resultMessage, 50, 100);
        }

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            selectedOption = 0;
            checkAnswer();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            selectedOption = 1;
            checkAnswer();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) {
            selectedOption = 2;
            checkAnswer();
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) {
            selectedOption = 3;
            checkAnswer();
        }
    }

    private void checkAnswer() {
        if (selectedOption == correctOption) {
            resultMessage = "Correto! Parabéns!";
        } else {
            resultMessage = "Errado! Tente novamente.";
        }
    }

    @Override
    public void show() {
        System.out.println("Tela de Quiz aberta.");
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
