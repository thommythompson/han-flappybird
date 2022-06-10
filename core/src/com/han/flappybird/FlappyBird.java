package com.han.flappybird;

import java.time.LocalTime;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.han.flappybird.Screens.StartScreen;

/**
* @version 1
* @author Thomas Hofman
* Dit is het "entrypoint" van de applicatie, de LibGDX engine roept deze klasse als eerst aan.
*/
public class FlappyBird extends Game {
	
	public SpriteBatch batch;
	private Texture backgroundImg;

	/**
	 * Deze methode wordt eenmalig aangeroepen bij het starten van de applicatie. De methode is verantwoordelijk voor het aanmaken van de SpriteBatch, roept de updateBackground methode aan en schakelt naar het eerste scherm.
	 */
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		updateBackground();
		setScreen(new StartScreen(this));
	}

	/**
	 * Deze methode vult het backgroundImg attribuut met een background texture aan de hand van het huidige tijstip.
	 */
	private void updateBackground(){

		// Het huidige hele uur ophalen
		int currentHour = LocalTime.now().getHour();

		// Indien het hele uur gelijk is aan 8 of 20 of daar tussen ligt word de dag background ingeladen, anders de nacht background
        if(currentHour >= 8 && currentHour <= 20) backgroundImg = new Texture("sprites/background-day.png");
        else backgroundImg = new Texture("sprites/background-night.png");
	}

	
	/**
	 * @return Texture
	 * Getter voor het backgroundImg attribuut.
	 */
	public Texture getBackground(){
		return backgroundImg;
	}

	/**
	 * Roept de render methode van de Game klasse aan.
	 */
	@Override
	public void render() {
		super.render();
	}
}
