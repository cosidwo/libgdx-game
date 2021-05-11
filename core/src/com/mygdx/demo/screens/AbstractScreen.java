package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.demo.LibGDXGame;

public abstract class AbstractScreen implements Screen {
    protected LibGDXGame libGDXGame;
    protected Stage stage;
    private OrthographicCamera orthographicCamera;

    protected SpriteBatch spriteBatch;

    public AbstractScreen(LibGDXGame libGDXGame){
        this.libGDXGame = libGDXGame;
        createCamera();
    }

    private void createCamera() {
        orthographicCamera = new OrthographicCamera();
        orthographicCamera.setToOrtho(false,LibGDXGame.WIDTH,LibGDXGame.HEIGHT);
        orthographicCamera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        //orthographicCamera.update();
        //spriteBatch.setProjectionMatrix(orthographicCamera.combined);
    }

    private void clearScreen() {
        Gdx.gl.glClearColor(0,0,0,0);
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
    public void resize(int width, int height) {}
}
