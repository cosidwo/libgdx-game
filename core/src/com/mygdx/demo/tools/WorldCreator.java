package com.mygdx.demo.tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.sprites.Brick;
import com.mygdx.demo.sprites.Surprise;

public class WorldCreator {

    public BodyDef bodyDef;
    public PolygonShape shape;
    public FixtureDef fixtureDef;
    public Body body;

    public WorldCreator(World world, TiledMap map){
        bodyDef =  new BodyDef();
        shape = new PolygonShape();
        fixtureDef = new FixtureDef();

        for(int i=2;i<7;i++) {
            for (MapObject object : map.getLayers().get(i).getObjects().getByType(RectangleMapObject.class)) {
                if(i==5){
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    new Surprise(world,map,rectangle);
                }
                else if(i==4){
                    Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
                    new Brick(world,map,rectangle);
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
