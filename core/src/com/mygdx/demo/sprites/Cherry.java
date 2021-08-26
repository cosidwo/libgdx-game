package com.mygdx.demo.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.screens.FirsLevelScreen;

public class Cherry extends TileObject{
    public Cherry(FirsLevelScreen screen, Rectangle rectangle) {
        super(screen, rectangle);
        fixtureDef.isSensor = true;
        fixture = body.createFixture(fixtureDef);

        setCategoryFilter(LibGDXGame.CHERRY_BIT);
        fixture.setUserData(this);
    }

    @Override
    public void onContact() {
        setCategoryFilter(LibGDXGame.CONTACT_BIT);
        getCell().setTile(null);
    }
}
