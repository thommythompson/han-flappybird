package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.FlappyBirdFont;
import com.han.flappybird.Entities.FlappyBirdFontSize;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het game over scherm.
*/
public class EndScreen extends FBScreen  {
    private Texture gameOverMessageImg;
    private boolean newHighScore;
    private FlappyBirdFont font;

    public EndScreen(FlappyBird game, boolean newHighScore){
        super(game);
        this.newHighScore = newHighScore;
    }

    /**
     * Binnen de show word een instantie van de spelwereld aangemaakt. Ook wordt het geluid ingeladen dat afgespeeld wordt wanneer de vogel in aanraking komt met schermranden en/of obstakels.
     */
    @Override
    public void runOnce() {
        gameOverMessageImg = new Texture("sprites/gameover.png");
        font = new FlappyBirdFont(FlappyBirdFontSize.Small);
    }

    /**
     * @param float delta
     * Binnen de render methode wordt de spelwereld aan de hand van de delta time geupate. 
     * Indien de wereld een botsing detecteert zal er een geluid afgespeeld worden en de game gefreezed worden voor 1 seconden waarnaar het over schakelt tot het game over scherm.
     * Indien er geen botsing gedetecteert is word de geupdate versie van de wereld op het scherm weergegeven.
     */
    @Override
    public void update(float delta) {
        if(Gdx.input.isTouched()) game.setScreen(new PlayScreen(game));
    }
    
    /**
     * @param float delta
     * Binnen de render methode wordt de spelwereld aan de hand van de delta time geupate. 
     * Indien de wereld een botsing detecteert zal er een geluid afgespeeld worden en de game gefreezed worden voor 1 seconden waarnaar het over schakelt tot het game over scherm.
     * Indien er geen botsing gedetecteert is word de geupdate versie van de wereld op het scherm weergegeven.
     */
    @Override
    public void draw(SpriteBatch batch){
        
        batch.draw(
            gameOverMessageImg, 
            ortoCam.viewportWidth / 4, // x positie
            ortoCam.viewportHeight / 4, // y positie
            ortoCam.viewportWidth / 2, // Breedte van de message
            ortoCam.viewportHeight / 12 // Hoogte van de message
        );

        int highScore = game.getHighScore();
        String message = "current highscore: " + highScore;
        if(newHighScore) message = "Congrats with your new highscore " + highScore + "!";

        font.draw(
            batch,
            message, 
            ortoCam.viewportHeight / 5 * 4
        );
    }
    
    /**
     * Bij het sluiten van het scherm word de ingeladen game over texture uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        gameOverMessageImg.dispose();
        font.dispose();
    }
}
