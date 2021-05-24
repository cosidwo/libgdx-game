package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.demo.LibGDXGame;
import com.mygdx.demo.scenes.Hud;

public class FirsLevelScreen extends AbstractScreen{

    private Hud hud;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;

    public FirsLevelScreen(LibGDXGame libGDXGame) {
        super(libGDXGame);
        hud = new Hud(libGDXGame.batch);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("tmx_maps/level1.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(map);
        orthographicCamera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
    }

    public void handleInput(float delta) {
        if(Gdx.input.isTouched()){
            orthographicCamera.position.x += 100*delta;
        }
    }

    public void update(float delta){
        handleInput(delta);
        orthographicCamera.update();
        mapRenderer.setView(orthographicCamera);
    }



    @Override
    public void render(float delta) {
        update(delta);
        super.render(delta);

        mapRenderer.render();

        libGDXGame.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }
}
