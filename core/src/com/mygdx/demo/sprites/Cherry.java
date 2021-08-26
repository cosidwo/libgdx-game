package com.mygdx.demo.sprites;

import com.badlogic.gdx.math.Rectangle;
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
        //when fox collides with cherry, the category filter of cherry is changed to CONTACT_BIT so fox can't collide with
        //the same cherry more than one time
        setCategoryFilter(LibGDXGame.CONTACT_BIT);

        //cherry sprite is removed
        getCell().setTile(null);
    }
}
