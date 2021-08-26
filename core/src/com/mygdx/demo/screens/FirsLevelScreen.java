package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.scenes.Hud;
import com.mygdx.demo.scenes.HudEndGame;
import com.mygdx.demo.sprites.Frog;
import com.mygdx.demo.sprites.Opossum;
import com.mygdx.demo.sprites.PlayerSprite;
import com.mygdx.demo.tools.WorldContactListener;
import com.mygdx.demo.tools.WorldCreator;

public class FirsLevelScreen extends AbstractScreen{

    private boolean paused;
    private long start_time;
    private long current_time;
    private int points;

    private Hud hud;
    private HudEndGame hudEndGame;

    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private WorldCreator worldCreator;
    private PlayerSprite player;
    private Opossum opossum;
    private Opossum opossum2;

    private Frog frog;
    private Frog frog2;

    private TextureAtlas atlas;

    private WorldContactListener worldContactListener;

    public FirsLevelScreen(LibGDXGame libGDXGame) {
        super(libGDXGame);
        paused = false;
        start_time = System.currentTimeMillis();
        atlas = new TextureAtlas("sprites/packed/Textures.atlas");
        hud = new Hud(libGDXGame.batch,0,0);
        hudEndGame = new HudEndGame(libGDXGame.batch);

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("sunnyland_map/sunnyland.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/LibGDXGame.PPM);
        orthographicCamera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0,-10), true);
        debugRenderer = new Box2DDebugRenderer();

        worldCreator = new WorldCreator(this);

        player = new PlayerSprite(this);
        opossum = new Opossum(this, 9f, .32f, 6f,9.3f);
        opossum2 = new Opossum(this, 12f, .32f,10.95f,13.65f);
        frog = new Frog(this,16.5f,.32f,16.1f,17.3f);
        frog2 = new Frog(this,19.5f,.32f,18.3f,19.3f);

        worldContactListener = new WorldContactListener(libGDXGame);
        world.setContactListener(worldContactListener);
    }

    @Override
    public void render(float delta) {
        update(delta);
        super.render(delta);

        //rendering game map
        mapRenderer.render();

        //rendering box2Ddebuglines
        debugRenderer.render(world, orthographicCamera.combined);

        libGDXGame.batch.setProjectionMatrix(orthographicCamera.combined);

        /////////////////////////////////////
        libGDXGame.batch.begin();           //
        player.draw(libGDXGame.batch);
        opossum.draw(libGDXGame.batch);
        opossum2.draw(libGDXGame.batch);
        frog.draw(libGDXGame.batch);
        frog2.draw(libGDXGame.batch);/// drawing fox's texture
        libGDXGame.batch.end();             //
        /////////////////////////////////////

        //check if fox collides with house
        if(worldContactListener.isPaused()){
            paused = true;
            hudEndGame.stage.draw();

        }

        //setting batch to draw what the camera sees
        libGDXGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);

        //update main hud only if game is not paused
        if(!paused)
            hud.stage.draw();
    }

    public void update(float delta){
        current_time = System.currentTimeMillis() - start_time;
        points = worldContactListener.getPoints();
        hud = new Hud(libGDXGame.batch,(int)current_time/1000,points);

        //stoping movement after player collided with house
        if(!paused){
            handleInput(delta);
        }else {
            if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                libGDXGame.create();
            }
        }

        //takes 1 step in the physics simulation
        world.step(1/60f,6,2);

        //updating player's sprite
        player.update(delta);

        //updating enemies' sprites
        opossum.update(delta);
        opossum2.update(delta);
        frog.update(delta);
        frog2.update(delta);

        //attaching camera to player's x coordinate
        orthographicCamera.position.x = player.body.getPosition().x;

        //updating camera with coordinates after changes
        orthographicCamera.update();

        //telling renderer to draw only what camera see
        mapRenderer.setView(orthographicCamera);

        if(player.body.getPosition().y < -1){
            libGDXGame.create();
        }
    }

    public void handleInput(float delta) {
        //implementing jump after hitting space button
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && player.body.getLinearVelocity().y==0)
            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);

        //implementing moving to right after hitting right arrow button
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <=2)
            player.body.applyLinearImpulse(new Vector2(0.1f,0),player.body.getWorldCenter(),true);

        //implementing moving to left after hitting left arrow button
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >=-2)
            player.body.applyLinearImpulse(new Vector2(-0.1f,0),player.body.getWorldCenter(),true);

    }

    @Override
    public void dispose() {
        super.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }

    public TextureAtlas getAtlas(){
        return atlas;
    }

    public TiledMap getMap(){
        return map;
    }

    public World getWorld(){
        return world;
    }
}
