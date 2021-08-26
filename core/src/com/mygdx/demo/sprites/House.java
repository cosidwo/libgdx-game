package com.mygdx.demo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.screens.FirsLevelScreen;

public class House extends TileObject{
    public House(FirsLevelScreen screen, Rectangle rectangle) {
        super(screen, rectangle);

        fixture = body.createFixture(fixtureDef);
        fixture.setUserData("house");
    }

    @Override
    public void onContact() {
    }
}
