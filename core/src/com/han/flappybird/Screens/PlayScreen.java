package com.han.flappybird.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.han.flappybird.FlappyBird;
import com.han.flappybird.Entities.World;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse extend de FBScreen klasse en representeert het speel scherm.
*/
public class PlayScreen extends FBScreen {
    private World world;
    private Sound die;

    public PlayScreen(FlappyBird game){
        super(game);
    }

    /**
     * Binnen de show word een instantie van de spelwereld aangemaakt. Ook wordt het geluid ingeladen dat afgespeeld wordt wanneer de vogel in aanraking komt met schermranden en/of obstakels.
     */
    @Override
    public void show() {
        super.show();

        die = Gdx.audio.newSound(Gdx.files.internal("audio/die.ogg"));
        world = new World();
    }

    /**
     * @param float delta
     * Binnen de render methode wordt de spelwereld aan de hand van de delta time geupate. 
     * Indien de wereld een botsing detecteert zal er een geluid afgespeeld worden en de game gefreezed worden voor 1 seconden waarnaar het over schakelt tot het game over scherm.
     * Indien er geen botsing gedetecteert is word de geupdate versie van de wereld op het scherm weergegeven.
     */
    @Override
    public void render(float delta) {
        super.render(delta);

        world.update(delta, Gdx.input.isTouched());

        if(world.colissionDetected()){
            die.play();
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                System.out.println("Interruption occured!");
            }
            game.setScreen(new EndScreen(game));
        }

        game.batch.begin();
        game.batch.draw(game.getBackground(), gameCam.position.x - (gameCam.viewportWidth / 2), gameCam.position.y - (gameCam.viewportHeight / 2), gameCam.viewportWidth, gameCam.viewportHeight);
        world.draw(game.batch);  
        game.batch.end();
    }

    /**
     * Bij het sluiten van het scherm word het ingeladen game over geluid en de spelwereld uit het geheugen verwijderd. 
     */
    @Override
    public void dispose() {
        world.dispose();
        die.dispose();
    }
}
