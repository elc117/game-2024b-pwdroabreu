package com.terminalroot.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

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

    @Override
    public void create() {
        batch = new SpriteBatch();
        background = new Texture("fundojogo1.png");

        Texture dinoTexture = new Texture("dino1.png");
        dinoSprite = new Sprite(dinoTexture);
        dinoSprite.setSize(dinoSprite.getWidth() * 0.5f, dinoSprite.getHeight() * 0.5f);
        dinoX = 100;
        dinoY = 100;

        objects = new ArrayList<>();

        // Configuração dos objetos interativos
        int numberOfObjects = 5; // Número de objetos
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        Texture objectTexture = new Texture("objeto.png");
        float objectWidth = objectTexture.getWidth() * (1f / 3f); // Reduzido em 1/3
        float spacing = 150; // Espaçamento fixo entre os objetos

        // Largura total ocupada pelos objetos (inclui espaçamento entre eles)
        float totalWidth = numberOfObjects * objectWidth + (numberOfObjects - 1) * spacing;

        // Posição inicial (x) centralizada
        float startX = (screenWidth - totalWidth) / 2;
        float y = (screenHeight / 2) - (objectWidth / 2); // Centraliza no eixo Y

        // Criar e posicionar os objetos
        for (int i = 0; i < numberOfObjects; i++) {
            float x = startX + i * (objectWidth + spacing); // Posiciona cada objeto
            objects.add(new InteractiveObject(objectTexture, x, y));
        }
    }

    @Override
    public void render() {
        super.render();
        ScreenUtils.clear(1, 0, 0, 1);
        float delta = Gdx.graphics.getDeltaTime();

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

    private void handleInput(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) {
            dinoY += speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            dinoY -= speed * delta;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            dinoX -= speed * delta;
            if (facingRight) {
                dinoSprite.flip(true, false);
                facingRight = false;
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
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
                System.out.println("Colidiu com objeto! Abrindo Quiz...");
                setScreen(new QuizScreen());
                return;
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        dinoSprite.getTexture().dispose();
        super.dispose();
        for (InteractiveObject obj : objects) {
            obj.dispose();
        }
    }
}
