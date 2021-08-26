package com.mygdx.demo.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.screens.FirsLevelScreen;
import com.mygdx.demo.sprites.Brick;
import com.mygdx.demo.sprites.Cherry;
import com.mygdx.demo.sprites.House;

public class WorldCreator {

    public World world;
    public TiledMap map;

    public BodyDef bodyDef;
    public PolygonShape shape;
    public FixtureDef fixtureDef;
    public Body body;

    public WorldCreator(FirsLevelScreen screen){
        world = screen.getWorld();
        map = screen.getMap();

        bodyDef =  new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        for(int i=10;i<=13;i++) {
            for (MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                if(i==13){
                    //creating cherries
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    new Cherry(screen,rectangle);
                }
                else if(i==12){
                    //creating house object
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    new House(screen,rectangle);
                }
                else if(i==11){
                    //creating bricks
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    new Brick(screen,rectangle);
                }else {
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

                    bodyDef.type = BodyDef.BodyType.StaticBody;
                    bodyDef.position.set((rectangle.getX() + rectangle.getWidth() / 2) / LibGDXGame.PPM, (rectangle.getY() + rectangle.getHeight() / 2) / LibGDXGame.PPM);
                    body = world.createBody(bodyDef);

                    shape.setAsBox(rectangle.getWidth() / 2 / LibGDXGame.PPM, rectangle.getHeight() / 2 / LibGDXGame.PPM);
                    fixtureDef.shape = shape;
                    body.createFixture(fixtureDef);
                }
            }
        }
    }
}
