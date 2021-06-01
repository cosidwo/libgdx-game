package com.mygdx.demo.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.LibGDXGame;

public class PlayerSprite extends Sprite {

    public World world;
    public Body body;

    public PlayerSprite(World world){
        this.world = world;
        defineSprite();
    }

    public void defineSprite() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / LibGDXGame.PPM, 32 / LibGDXGame.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(5 / LibGDXGame.PPM);

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
