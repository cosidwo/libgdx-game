package com.mygdx.demo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.demo.LibGDXGame;

public class TileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle rectangle;
    protected Body body;

    public TileObject(World world,TiledMap map, Rectangle rectangle){
        this.world = world;
        this.map = map;
        this.rectangle = rectangle;

        BodyDef bodyDef = new BodyDef();
        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((float)(rectangle.getX() + rectangle.getWidth() / 2) / LibGDXGame.PPM, (float)(rectangle
                .getY() + rectangle.getHeight() / 2) / LibGDXGame.PPM);
        body = world.createBody(bodyDef);

        shape.setAsBox((float)rectangle.getWidth() / 2 / LibGDXGame.PPM, (float)rectangle.getHeight() / 2 / LibGDXGame.PPM);
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }
}
