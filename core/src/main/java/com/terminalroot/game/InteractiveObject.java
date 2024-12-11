package com.terminalroot.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InteractiveObject {
    private final Texture texture;
    private final float x;
    private final float y;
    private final String question;
    private final String[] options;
    private final int correctAnswerIndex;
    private final float scale;

    public InteractiveObject(Texture texture, float x, float y, String question, String[] options, int correctAnswerIndex, float scale) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.scale = scale;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth() * scale, texture.getHeight() * scale); // Aplicar escala aqui
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, texture.getWidth() * scale, texture.getHeight() * scale); // Ajustar bounds com escala
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public boolean isCorrectAnswer(int index) {
        return index == correctAnswerIndex;
    }

    public void dispose() {
        texture.dispose();
    }
}
