package com.terminalroot.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InteractiveObject {
    private final Texture texture;
    private final float x;
    private final float y;
    private float width; // Removido final para permitir ajuste dinâmico
    private float height; // Removido final para permitir ajuste dinâmico

    public InteractiveObject(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.width = texture.getWidth() * (1f / 3f);
        this.height = texture.getHeight() * (1f / 3f);
    }

    // Método para redimensionar dinamicamente o objeto
    public void setSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height); // Usa as dimensões atualizadas
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
