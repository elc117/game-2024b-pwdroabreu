package com.terminalroot.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class InteractiveObject {
    private final Texture texture;
    private final float x;
    private final float y;
    private final float width;
    private final float height;

    public InteractiveObject(Texture texture, float x, float y) {
        this.texture = texture;
        this.x = x;
        this.y = y;


        this.width = texture.getWidth() * (1f / 3f);
        this.height = texture.getHeight() * (1f / 3f);
    }

    public void draw(SpriteBatch batch) {
        batch.draw(texture, x, y, width, height); // usa as dimensões reduzidas
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }
}
