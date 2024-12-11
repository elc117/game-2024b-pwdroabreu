package com.terminalroot.game;

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


import java.util.ArrayList;
import java.util.List;

public class Main extends Game {
    private SpriteBatch batch;
    private Texture background;
    private Sprite dinoSprite;
    private List<InteractiveObject> objects;

    private float dinoX, dinoY;
    private boolean facingRight = true;

    private float speed = 200f;

    private boolean quizActive = false;
    private InteractiveObject currentObject;
    private int selectedOption = 0;

    private BitmapFont font;

    @Override
public void create() {
    batch = new SpriteBatch();
    background = new Texture("fundojogo1.png");

    font = new BitmapFont();
    font.getData().setScale(2);

    Texture dinoTexture = new Texture("dino1.png");
    dinoSprite = new Sprite(dinoTexture);
    dinoSprite.setSize(dinoSprite.getWidth() * 0.5f, dinoSprite.getHeight() * 0.5f);
    dinoX = 100;
    dinoY = 100;

    objects = new ArrayList<>();
    Texture objectTexture = new Texture("objeto.png");


    String[] questions = {
        "Qual destas espécies foi descoberta \n no Geopark Quarta Colônia? \n",
        "Qual rio está presente no Geopark Caçapava?",
        "Quando foi oficializado o primeiro \n distrito criativo de Santa Maria? \n",
        "Quando foi fundado o Jardim Botânico da UFSM?",
        "Em que ano o Geoparque Quarta Colônia \n foi certificado como Geoparque Mundial da Unesco? \n"
    };

    String[][] options = {
        {"Bagualosaurus agudoensis", "Stegosaurus stenops", "Tyrannosaurus rex"},
        {"Rio Camaquã", "Rio Uruguai", "Rio Solimões"},
        {"2022", "2010", "2018"},
        {"1981", "1975", "1990"},
        {"2014", "2023", "2001"}
    };

    int[] correctAnswers = {0, 0, 2, 0, 1};


    float screenWidth = Gdx.graphics.getWidth();
    float screenHeight = Gdx.graphics.getHeight();
    float scale = 0.1f;
    float objectWidth = objectTexture.getWidth() * scale;
    float objectHeight = objectTexture.getHeight() * scale;
    float spacing = 250;

    float totalWidth = questions.length * objectWidth + (questions.length - 1) * spacing;
    float startX = (screenWidth - totalWidth) / 2;
    float y = (screenHeight - objectHeight) / 2;


    for (int i = 0; i < questions.length; i++) {
        float x = startX + i * (objectWidth + spacing);
        InteractiveObject obj = new InteractiveObject(objectTexture, x, y, questions[i], options[i], correctAnswers[i], 0.5f);
        objects.add(obj);
    }
}



    @Override
    public void render() {
        super.render();
        if (quizActive) {
            renderQuiz();
        } else {
            float delta = Gdx.graphics.getDeltaTime();
            ScreenUtils.clear(1, 0, 0, 1);
            handleInput(delta);
            checkInteractions();

            batch.begin();
            batch.draw(background, 0, 0);
            for (InteractiveObject obj : objects) {
                obj.draw(batch);
            }
            dinoSprite.setPosition(dinoX, dinoY);
            dinoSprite.draw(batch);
            batch.end();
        }
    }

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dinoY += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dinoY -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dinoX -= speed * delta;
            if (facingRight) {
                dinoSprite.flip(true, false);
                facingRight = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dinoX += speed * delta;
            if (!facingRight) {
                dinoSprite.flip(true, false);
                facingRight = true;
            }
        }

        dinoX = Math.max(0, Math.min(dinoX, Gdx.graphics.getWidth() - dinoSprite.getWidth()));
        dinoY = Math.max(0, Math.min(dinoY, Gdx.graphics.getHeight() - dinoSprite.getHeight()));
    }

    private void checkInteractions() {
        Rectangle dinoBounds = dinoSprite.getBoundingRectangle();

        for (InteractiveObject obj : objects) {
            if (dinoBounds.overlaps(obj.getBounds())) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                    currentObject = obj;
                    quizActive = true;
                    return;
                }
            }
        }
    }

    private boolean showFeedback = false;
    private String feedbackMessage = "";
    private float feedbackTimer = 0;

    private void renderQuiz() {
        ScreenUtils.clear(0, 0, 0, 1);

        batch.begin();


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
            } else {
                feedbackMessage = "Errado! Tente Novamente!";
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

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        dinoSprite.getTexture().dispose();
        font.dispose();
        for (InteractiveObject obj : objects) {
            obj.dispose();
        }
    }
}
