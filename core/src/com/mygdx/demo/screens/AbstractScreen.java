package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.LibGDXGame;

public abstract class AbstractScreen implements Screen {
    protected LibGDXGame libGDXGame;
    protected Stage stage;
    protected OrthographicCamera orthographicCamera;
    protected Viewport viewport;
    protected SpriteBatch spriteBatch;

    public AbstractScreen(LibGDXGame libGDXGame){
        this.libGDXGame = libGDXGame;
        orthographicCamera = new OrthographicCamera();
        viewport = new FitViewport(LibGDXGame.WIDTH /LibGDXGame.PPM,LibGDXGame.HEIGHT /LibGDXGame.PPM,orthographicCamera);
    }

    @Override
    public void render(float delta) {
        clearScreen();
        //orthographicCamera.update();
        //spriteBatch.setProjectionMatrix(orthographicCamera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resume() {
        libGDXGame.setPaused(false);
    }

    @Override
    public void pause() {
        libGDXGame.setPaused(true);
    }

    @Override
    public void dispose() {
        libGDXGame.dispose();
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,false);
    }
}
