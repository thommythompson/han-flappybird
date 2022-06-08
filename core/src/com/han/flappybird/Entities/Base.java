package com.han.flappybird.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.han.flappybird.FlappyBird;

/**
* @version 1
* Deze klasse representeert het grond object.
*/
public class Base extends WorldObstacle{
    public static final Vector2 MEASUREMENTS = new Vector2(FlappyBird.CAM_WIDTH, FlappyBird.CAM_WIDTH / 4);

    Base(float xPos, float yPos, float width, float height){
        super(xPos, yPos, MEASUREMENTS.x, MEASUREMENTS.y);
        texture = new Texture("sprites/base.png");
    }

    /**
     * De update methode is overschreven zodat hier funtionaliteit aan toegevoegd kan worden. Wanneer het object zich namelijk links buiten het scherm bevind zal het zelf zijn positie resetten. 
     */
    @Override
    public void update(float delta){
        super.update(delta);
        if(isOffScreen()) resetPosition();
    }

    /**
     * Deze methode is verantwoordelijk voor het daadwerkelijk resetten van de positie van het grond object, na het aanroepen van de methode zal het grond object zich rechts buiten het scherm begeven.
     */
    private void resetPosition(){
        Vector2 newPosition = new Vector2(getPosition().x + (bounds.width * 2), getPosition().y);
        setPosition(newPosition);
    }
}