package com.mygdx.demo.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.demo.screens.FirsLevelScreen;

public class House extends TileObject{
    public House(FirsLevelScreen screen, Rectangle rectangle) {
        super(screen, rectangle);

        fixture = body.createFixture(fixtureDef);
        //setting user data so house object can be distinguished in WorldContactListener class during collision detection
        fixture.setUserData("house");
    }

    @Override
    public void onContact() {
    }
}
