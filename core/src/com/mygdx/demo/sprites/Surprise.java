package com.mygdx.demo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.demo.LibGDXGame;
import com.badlogic.gdx.math.Rectangle;

public class Surprise extends TileObject{

    public Surprise(World world, TiledMap map, Rectangle rectangle) {
        super(world, map, rectangle);
    }
}
