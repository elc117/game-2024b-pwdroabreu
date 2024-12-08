package com.terminalroot.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InteractiveObject {
    private Texture texture;
    private float x, y;
    private float width, height; // Adiciona controle sobre largura e altura

    public InteractiveObject(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;

        // Reduz o tamanho em 1/3
        this.width = texture.getWidth() * (1f / 3f);
        this.height = texture.getHeight() * (1f / 3f);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height); // Usa as dimensões reduzidas
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height); // Atualiza o cálculo do retângulo de colisão
    }

    public void dispose() {
        texture.dispose();
    }
}
