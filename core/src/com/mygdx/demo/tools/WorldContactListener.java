package com.mygdx.demo.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.scenes.Hud;
import com.mygdx.demo.scenes.HudEndGame;
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

        if(fixA.getUserData()=="house" || fixB.getUserData()=="house"){
            pause = true;
        }

        if(fixA.getUserData()=="fox" || fixB.getUserData()=="fox"){

            Fixture fox = fixA.getUserData() == "fox" ? fixA : fixB;
            Fixture object = fox == fixA ? fixB : fixA;

            if (object.getUserData() != null && Cherry.class.isAssignableFrom(object.getUserData().getClass())) {
                points++;
                Gdx.app.log("tag","contact2!");
                ((TileObject) object.getUserData()).onContact();
            }
        }

        if((fixA.getUserData()=="opossum" && fixB.getUserData()=="fox")|| (fixA.getUserData()=="fox" && fixB.getUserData()=="opossum")){
            libGDXGame.create();
        }

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
