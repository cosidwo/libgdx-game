package com.mygdx.demo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

public class Brick extends TileObject{

    public Brick(World world, TiledMap map, Rectangle rectangle) {
        super(world, map, rectangle);
    }
}
