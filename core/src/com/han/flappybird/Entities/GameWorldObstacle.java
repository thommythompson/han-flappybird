package com.han.flappybird.Entities;

import com.badlogic.gdx.utils.Array;

/**
* @version 1
* @author Thomas Hofman
* Deze abstracte klasse extend de WorldObject klasse. 
* Het enigste doel van deze klasse is het bijhouden van referenties naar alle obstakels die er binnen de applicatie aangemaakt worden zodat deze in bulk aangeroepen kunnen worden.
*/
public abstract class GameWorldObstacle extends GameWorldObject{
    private static Array<GameWorldObject> instances = new Array<GameWorldObject>();

    GameWorldObstacle(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height);
        instances.add(this);
    }

    /**
     * @Return Array<WorldObstacle>
     * Geeft een array terug gevuld met referenties naar alle geinstantieerde WorldObstacles.
     */
    public static Array<GameWorldObject> getAllInstances(){
        return instances;
    }

    /**
     * Verwijderd alle Textures die ingeladen zijn door instanties van het WorldObject type uit het geheugen en leegt vervolgens de statisch array waarin het referenties naar instanties van het eigen type bijhoud.
     */
    public static void disposeAllInstances(){
        for(GameWorldObject worldObject : GameWorldObstacle. getAllInstances()) worldObject.disposeTexture();
        instances.clear();
    }
}