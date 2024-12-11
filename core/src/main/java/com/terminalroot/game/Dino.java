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

public class Dino {
    private Sprite dinoSprite;
    private float dinoX, dinoY;
    private boolean facingRight = true;
    private final float speed = 500f;

    public Dino(Texture texture, float x, float y, float scale) {
        this.dinoSprite = new Sprite(texture);
        this.dinoSprite.setScale(scale);
        this.dinoSprite.setSize(
            dinoSprite.getWidth() * scale,
            dinoSprite.getHeight() * scale
        );
        this.dinoX = x;
        this.dinoY = y;
    }


    public void handleInput(float delta) {
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

    public Rectangle getBounds() {
        return new Rectangle(dinoX, dinoY, dinoSprite.getWidth(), dinoSprite.getHeight());
    }

    public void dispose() {
        dinoSprite.getTexture().dispose();
    }

    public Sprite getSprite() {
        return dinoSprite;
    }

    public float getX() {
        return dinoX;
    }

    public float getY() {
        return dinoY;
    }
}
