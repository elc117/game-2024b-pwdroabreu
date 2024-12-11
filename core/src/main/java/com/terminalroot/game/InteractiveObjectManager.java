package com.terminalroot.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.terminalroot.game.Dino;
import com.terminalroot.game.QuizManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;



public class InteractiveObjectManager {
    private List<InteractiveObject> objects;
    private InteractiveObject currentObject;

    public void loadObjects() {
        objects = new ArrayList<>();

        // Configuração das perguntas e respostas
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

        Texture objectTexture = new Texture("objeto.png");
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float scale = 0.4f; // Escala aplicada à textura
        float objectWidth = objectTexture.getWidth() * scale;
        float objectHeight = objectTexture.getHeight() * scale;
        float spacing = 200; // Espaçamento baseado no tamanho do objeto

        float totalWidth = questions.length * objectWidth + (questions.length - 1) * spacing;
        float startX = (screenWidth - totalWidth) / 2;
        float y = (screenHeight - objectHeight) / 2;


        for (int i = 0; i < questions.length; i++) {
            float x = startX + i * (objectWidth + spacing);
            InteractiveObject obj = new InteractiveObject(objectTexture, x, y, questions[i], options[i], correctAnswers[i], scale);
            objects.add(obj);

        }
    }


    public InteractiveObject getCurrentObject() {
        return currentObject;
    }


    public void checkInteractions(Dino dino) {
        Rectangle dinoBounds = dino.getBounds();
        currentObject = null; // Resetar o objeto atual

        for (InteractiveObject obj : objects) {
            if (dinoBounds.overlaps(obj.getBounds())) {
                currentObject = obj; // Salvar o objeto com o qual houve colisão
                break;
            }
        }
    }


    public List<InteractiveObject> getObjects() {
        return objects;
    }

    public void dispose() {
        for (InteractiveObject obj : objects) {
            obj.dispose();
        }
    }
}
