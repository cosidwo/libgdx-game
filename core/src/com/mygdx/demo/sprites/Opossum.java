package com.mygdx.demo.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.utils.Array;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.screens.FirsLevelScreen;

public class Opossum extends Enemy{

    private float boundX, boundY;
    private TextureRegion textureRegion;

    private Array<TextureRegion> array;

    private boolean runningRight = false;

    public Opossum(FirsLevelScreen screen, float x, float y, float boundX, float boundY) {
        super(screen, x, y);
        this.boundX = boundX;
        this.boundY = boundY;
        array = new Array<TextureRegion>();
        textureRegion = new TextureRegion(screen.getAtlas().findRegion("opossum-5"),2,10,28,25);
        setBounds(getX(),getY(),21/LibGDXGame.PPM, 21/LibGDXGame.PPM);
        velocity = new Vector2(-1,0);
    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y = getHeight()/2);
        setRegion(textureRegion);
        body.setLinearVelocity(velocity);
        if(body.getPosition().x <= boundX){
            velocity.x = 1;
        }
        else if(body.getPosition().x >= boundY){
            velocity.x = -1;
        }
        getFrame(delta);
    }

    private void getFrame(float delta){
        if((body.getLinearVelocity().x < 0 || !runningRight) && textureRegion.isFlipX()){
            textureRegion.flip(true,false);
            runningRight = false;
        } else if((body.getLinearVelocity().x > 0 || runningRight) && !textureRegion.isFlipX()){
            textureRegion.flip(true,false);
            runningRight = true;
        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(7 / LibGDXGame.PPM);

        //defining category bit of opossum
        fixtureDef.filter.categoryBits = LibGDXGame.ENEMY_BIT;
        //adding mask bits - defining objects which opossum is able to collide with
        fixtureDef.filter.maskBits = LibGDXGame.FOX_BIT | LibGDXGame.DEFAULT_BIT | LibGDXGame.ENEMY_BIT;

        fixtureDef.shape = shape;

        //setting user data so opossum object can be distinguished in WorldContactListener class during collision detection
        body.createFixture(fixtureDef).setUserData("opossum");
    }
}
