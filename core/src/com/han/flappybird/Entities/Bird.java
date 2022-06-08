package com.han.flappybird.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze klasse representeert de vogel die door de speler bestuurt wordt.
*/
public class Bird extends WorldObject{
    public static final Vector2 MEASUREMENTS = new Vector2(30,20);
    private Vector2 velocity;
    private Vector2 acceleration;
    private Animation<Texture> animation;
    private Array<Texture> textures;
    private float timeElapsed;
    private Sound wing;

    public Bird(float xPos, float yPos, float width, float height){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, -600);
        wing = Gdx.audio.newSound(Gdx.files.internal("audio/wing.ogg"));
        
        setAnimation();
    }

    /** 
     * Deze methode update de positie van de vogel aan de hand van de delta time en bepaald de waarde van het texture attribuut aan de hand van de verstreken tijd.
     */
    @Override
    public void update(float delta){
        timeElapsed += delta;
        texture = animation.getKeyFrame(timeElapsed);
        updatePosition(delta);
    }

    /** 
     * Bepaald willekeurig de kleur die vogel krijgt en vult het animation attribuut.
     */
    private void setAnimation(){

        Random random = new Random();
        int birdNr = random.nextInt(3) + 1;

        textures = new Array<Texture>();

        switch(birdNr){
            case 1:
                textures.add(new Texture("sprites/bird-blue_0.png"));
                textures.add(new Texture("sprites/bird-blue_1.png"));
                textures.add(new Texture("sprites/bird-blue_2.png"));
                break;
            case 2:
                textures.add(new Texture("sprites/bird-red_0.png"));
                textures.add(new Texture("sprites/bird-red_1.png"));
                textures.add(new Texture("sprites/bird-red_2.png"));
                break;
            case 3:
                textures.add(new Texture("sprites/bird-yellow_0.png"));
                textures.add(new Texture("sprites/bird-yellow_1.png"));
                textures.add(new Texture("sprites/bird-yellow_2.png"));
                break;
        }

        animation = new Animation<Texture>(0.5f, textures, PlayMode.LOOP);
    }

    /** 
     * Deze methode update de positie van de vogel aan de hand van de delta time en zorgt voor een voortdurend versnelende val.
     */    
    private void updatePosition(float delta){
        velocity.add(acceleration.cpy().scl(delta));
        if (velocity.y > 180) velocity.y = 180;
        Vector2 position = getPosition().add(velocity.cpy().scl(delta));
        bounds.setPosition(position);
    }

    /** 
     * Wanneer deze methode wordt aangeroepen zal de positie van de vogel verhoogd worden, ook wordt er een geluid afgespeelt. 
     */  
    public void jump(){
        velocity.y = 140;
        wing.play();
    }
}
