package com.mygdx.demo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.demo.LibGDXGame;

public class MenuScreen extends AbstractScreen{

    private final static int BUTTON_X = 340;
    private final static int START_BUTTON_Y = 350;
    private final static int EXIT_BUTTON_Y = 90;
    private Texture backgroundTexture, startButton, exitButton, startButtonHover, exitButtonHover;
    private SpriteBatch spriteBatch;

    public MenuScreen(LibGDXGame libGDXGame) {
        super(libGDXGame);
        backgroundTexture = new Texture("backgrounds/background-menu.png");
        startButton = new Texture("buttons/start-button.png");
        exitButton = new Texture("buttons/exit-button.png");
        startButtonHover = new Texture("buttons/start-button-hover.png");
        exitButtonHover = new Texture("buttons/exit-button-hover.png");
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        spriteBatch.begin();
        renderBackground();
        spriteBatch.end();
    }

    private void renderBackground() {
        spriteBatch.draw(backgroundTexture,0,0);
        if((Gdx.input.getX()>=BUTTON_X && Gdx.input.getX()<=BUTTON_X+120) && (Gdx.input.getY()>=EXIT_BUTTON_Y && Gdx.input.getY()<=EXIT_BUTTON_Y+40)){
            spriteBatch.draw(startButtonHover,BUTTON_X,START_BUTTON_Y);
            if(Gdx.input.isTouched()){
                libGDXGame.setScreen(new FirsLevelScreen(libGDXGame));
            }
        }
        else spriteBatch.draw(startButton,BUTTON_X,START_BUTTON_Y);

        if((Gdx.input.getX()>=BUTTON_X && Gdx.input.getX()<=BUTTON_X+120) && (Gdx.input.getY()>=START_BUTTON_Y && Gdx.input.getY()<=START_BUTTON_Y+40)) {
            spriteBatch.draw(exitButtonHover, BUTTON_X, EXIT_BUTTON_Y);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else spriteBatch.draw(exitButton,BUTTON_X,EXIT_BUTTON_Y);
    }

    @Override
    public void dispose() {
        super.dispose();
        //backgroundTexture.dispose();
    }
}
