package com.terminalroot.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
    private SpriteBatch batch;
    private int lives; // Sistema de vidas
    private int correctAnswers; // Contador de respostas corretas

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this)); // Começa na tela de menu
    }
    public int getLives() {
        return lives;
    }



    public void startGame() {
        // Reinicia o jogo
        lives = 3; // Três vidas no início
        correctAnswers = 0; // Zera as respostas corretas
        setScreen(new GameScreen(this, batch)); // Vai para o jogo principal
    }

    public void incrementCorrectAnswers() {
        correctAnswers++;
        if (correctAnswers == 5) {
            setScreen(new VictoryScreen(this)); // Vai para a tela de vitória
        }
    }

    public void decrementLives() {
        lives--;
        if (lives <= 0) {
            setScreen(new GameOverScreen(this)); // Vai para a tela de Game Over
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
