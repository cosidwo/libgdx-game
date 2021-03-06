package com.mygdx.demo.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.screens.FirsLevelScreen;

public abstract class Enemy extends Sprite {

    protected World world;
    protected FirsLevelScreen screen;
    public Body body;
    public Vector2 velocity;

    public Enemy(FirsLevelScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x,y);
        defineEnemy();
        velocity = new Vector2(1,0);
    }

    protected  abstract void defineEnemy();
}
