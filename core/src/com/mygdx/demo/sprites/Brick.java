package com.mygdx.demo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.screens.FirsLevelScreen;

public class Brick extends TileObject{

    public Brick(FirsLevelScreen screen, Rectangle rectangle) {
        super(screen, rectangle);
        fixture = body.createFixture(fixtureDef);
    }

    @Override
    public void onContact() {

    }
}
