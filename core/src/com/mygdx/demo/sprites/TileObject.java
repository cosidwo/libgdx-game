package com.mygdx.demo.sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.screens.FirsLevelScreen;

public abstract class TileObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle rectangle;
    protected Body body;
    protected Fixture fixture;
    protected FixtureDef fixtureDef;

    public TileObject(FirsLevelScreen screen, Rectangle rectangle){
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.rectangle = rectangle;

        BodyDef bodyDef = new BodyDef();
        fixtureDef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set((float)(rectangle.getX() + rectangle.getWidth() / 2) / LibGDXGame.PPM, (float)(rectangle
                .getY() + rectangle.getHeight() / 2) / LibGDXGame.PPM);
        body = world.createBody(bodyDef);

        shape.setAsBox((float)rectangle.getWidth() / 2 / LibGDXGame.PPM, (float)rectangle.getHeight() / 2 / LibGDXGame.PPM);
        fixtureDef.shape = shape;

    }

    public abstract void onContact();

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell(){
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(9);
        return layer.getCell((int)(body.getPosition().x * LibGDXGame.PPM/ 16),
                (int)(body.getPosition().y * LibGDXGame.PPM / 16));
    }
}
