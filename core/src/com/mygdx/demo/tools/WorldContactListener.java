package com.mygdx.demo.tools;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.sprites.Cherry;
import com.mygdx.demo.sprites.TileObject;

public class WorldContactListener implements ContactListener {

    private LibGDXGame libGDXGame;
    private boolean pause=false;
    private int points = 0;
    private DelayedRemovalArray<Cherry> cherries;

    public WorldContactListener(LibGDXGame libGDXGame){
        this.libGDXGame = libGDXGame;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        //called when player collides with the house -> game pauses and the new hud is displayed
        if(fixA.getUserData()=="house" || fixB.getUserData()=="house"){
            pause = true;
        }

        //called when fox collides with any object
        if(fixA.getUserData()=="fox" || fixB.getUserData()=="fox"){

            //creating a fixture of fox
            Fixture fox = fixA.getUserData() == "fox" ? fixA : fixB;
            //creating a fixture of object which collided with fox
            Fixture object = fox == fixA ? fixB : fixA;

            //if object which collided with fox is a cherry then...
            if (object.getUserData() != null && Cherry.class.isAssignableFrom(object.getUserData().getClass())) {
                //...points are added
                points++;
                //...method onContact() is called
                ((TileObject) object.getUserData()).onContact();
            }
        }

        //called when fox collides with enemy opossum (game restarts)
        if((fixA.getUserData()=="opossum" && fixB.getUserData()=="fox")|| (fixA.getUserData()=="fox" && fixB.getUserData()=="opossum")){
            libGDXGame.create();
        }
        //called when fox collides with enemy frog (game restarts)
        if((fixA.getUserData()=="frog" && fixB.getUserData()=="fox")|| (fixA.getUserData()=="fox" && fixB.getUserData()=="frog")){
            libGDXGame.create();
        }
    }

    @Override
    public void endContact(Contact contact) {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    //Getters
    public boolean isPaused(){
        return pause;
    }

    public int getPoints() {
        return points;
    }
}
