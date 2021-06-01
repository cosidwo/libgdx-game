package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.scenes.Hud;
import com.mygdx.demo.sprites.PlayerSprite;
import com.mygdx.demo.tools.WorldCreator;

public class FirsLevelScreen extends AbstractScreen{

    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    private World world;
    private Box2DDebugRenderer debugRenderer;

    private WorldCreator worldCreator;
    private PlayerSprite player;

    public FirsLevelScreen(LibGDXGame libGDXGame) {
        super(libGDXGame);
        hud = new Hud(libGDXGame.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tmx_maps/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map, 1/LibGDXGame.PPM);
        orthographicCamera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        world = new World(new Vector2(0,-10), true);
        debugRenderer = new Box2DDebugRenderer();

        worldCreator = new WorldCreator(world,map);
        player = new PlayerSprite(world);

    }

    public void handleInput(float delta) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP))
            player.body.applyLinearImpulse(new Vector2(0,4f),player.body.getWorldCenter(),true);


        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.body.getLinearVelocity().x <=2)
            player.body.applyLinearImpulse(new Vector2(0.1f,0),player.body.getWorldCenter(),true);


        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.body.getLinearVelocity().x >=-2)
            player.body.applyLinearImpulse(new Vector2(-0.1f,0),player.body.getWorldCenter(),true);

    }

    public void update(float delta){
        handleInput(delta);

        world.step(1/60f,6,2);

        orthographicCamera.position.x = player.body.getPosition().x;

        orthographicCamera.update();
        mapRenderer.setView(orthographicCamera);
    }



    @Override
    public void render(float delta) {
        update(delta);
        super.render(delta);

        mapRenderer.render();

        debugRenderer.render(world, orthographicCamera.combined);

        libGDXGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void dispose() {
        super.dispose();
        mapRenderer.dispose();
        world.dispose();
        debugRenderer.dispose();
        hud.dispose();
    }
}
