package com.mygdx.demo.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.screens.FirsLevelScreen;
import org.w3c.dom.Text;

public class PlayerSprite extends Sprite {
    public enum State {STANDING, RUNNING}
    public State currentState;
    public State previousState;
    public World world;
    public Body body;
    private TextureRegion foxStand;
    private Animation foxRun;
    private Animation foxStandAnimation;
    private boolean runningRight;
    private float stateTimer;

    public PlayerSprite(FirsLevelScreen screen){
        //constructor from Screen class
        super(screen.getAtlas().findRegion("player-idle"));
        this.world = screen.getWorld();
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();
        for(int i=0;i<4;i++){
            frames.add(new TextureRegion(getTexture(),(i*21)+8,12,20,22));
        }
        foxRun = new Animation(0.1f,frames);
        frames.clear();

        Array<TextureRegion> frames2 = new Array<TextureRegion>();
        for(int i=0;i<4;i++){
            frames2.add(new TextureRegion(getTexture(),(i*21)+8,12,20,22));
        }
        foxStandAnimation= new Animation(0.1f,frames2);
        frames2.clear();

        foxStand = new TextureRegion(getTexture(),6,12,20,22);
        defineSprite();
        setBounds(0,0,16/LibGDXGame.PPM, 16/LibGDXGame.PPM);
        setRegion(foxStand);
    }

    public void defineSprite() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / LibGDXGame.PPM, 32 / LibGDXGame.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();

        CircleShape shape = new CircleShape();
        shape.setRadius(7 / LibGDXGame.PPM);

        fixtureDef.filter.categoryBits = LibGDXGame.FOX_BIT;
        fixtureDef.filter.maskBits = LibGDXGame.DEFAULT_BIT | LibGDXGame.CHERRY_BIT | LibGDXGame.ENEMY_BIT;

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef).setUserData("fox");

    }

    public void update(float delta){
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
        setRegion(getFrame(delta));
    }

    public TextureRegion getFrame(float delta){
        currentState = getState();

        TextureRegion region = null;
        switch(currentState){
            case RUNNING:
                region = (TextureRegion) foxRun.getKeyFrame(stateTimer,true);
                break;
            case STANDING:
                region = (TextureRegion) foxStandAnimation.getKeyFrame(stateTimer, true);
                break;
        }

        //flipping fox's sprite after change of direction
        if((body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()){
            region.flip(true,false);
            runningRight = false;
        } else if((body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()){
            region.flip(true,false);
            runningRight = true;
        }

        stateTimer = currentState == previousState ? stateTimer + delta : 0;
        previousState = currentState;
        return region;

    }

    public State getState(){
        if(body.getLinearVelocity().x!= 0){
            return State.RUNNING;
        } else return State.STANDING;
    }
}
