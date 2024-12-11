package com.terminalroot.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Gdx;



public class QuizManager {
    private boolean quizActive = false;
    private InteractiveObject currentObject;
    private int selectedOption = 0;
    private BitmapFont font;
    private SpriteBatch batch;
    private boolean showFeedback = false;
    private String feedbackMessage = "";
    private float feedbackTimer = 0;

    private Texture background;
    private final Main game;

    public QuizManager(SpriteBatch batch, Main game) {
        this.batch = batch;
        this.game = game;
        font = new BitmapFont();
        background = new Texture("fundojogo1.png");
    }

    public void startQuiz(InteractiveObject obj) {
        currentObject = obj;
        quizActive = true;
    }

    public boolean isQuizActive() {
        return quizActive;
    }

    public void renderQuiz() {
        ScreenUtils.clear(0, 0, 0, 1);
        batch.begin();

        // Renderiza o fundo e o quiz
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.getData().setScale(3);
        font.setColor(0, 0, 0, 1);

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        GlyphLayout layout = new GlyphLayout();
        String question = currentObject.getQuestion();
        layout.setText(font, question);
        float questionWidth = layout.width;
        float questionX = (screenWidth - questionWidth) / 2;
        float questionY = screenHeight - 200;

        font.draw(batch, question, questionX, questionY);

        String[] options = currentObject.getOptions();
        for (int i = 0; i < options.length; i++) {
            String prefix = i == selectedOption ? "> " : "  ";
            String optionText = prefix + options[i];

            layout.setText(font, optionText);
            float optionWidth = layout.width;
            float optionX = (screenWidth - optionWidth) / 2;
            float optionY = questionY - 100 - (i * 50);

            font.draw(batch, optionText, optionX, optionY);
        }

        if (showFeedback) {
            layout.setText(font, feedbackMessage);
            float feedbackWidth = layout.width;
            float feedbackX = (screenWidth - feedbackWidth) / 2;
            float feedbackY = questionY - 300;
            font.draw(batch, feedbackMessage, feedbackX, feedbackY);
        }

        batch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            selectedOption = (selectedOption - 1 + options.length) % options.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            selectedOption = (selectedOption + 1) % options.length;
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            if (currentObject.isCorrectAnswer(selectedOption)) {
                feedbackMessage = "Acertou! Parabéns!";
                game.incrementCorrectAnswers();
            } else {
                feedbackMessage = "Errado! Tente novamente!";
                game.decrementLives();
            }
            showFeedback = true;
            feedbackTimer = 2f;
        }

        if (showFeedback) {
            feedbackTimer -= Gdx.graphics.getDeltaTime();
            if (feedbackTimer <= 0) {
                showFeedback = false;
                quizActive = false;
            }
        }
    }

    public void dispose() {
        font.dispose();
    }
}
