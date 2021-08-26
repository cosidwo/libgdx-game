package com.mygdx.demo.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.demo.LibGDXGame;

public class HudEndGame implements Disposable {

    public Stage stage;
    private Viewport viewport;

    private Label mainLabel;
    private Label secondaryLabel;
    private Table table;

    public HudEndGame(SpriteBatch spriteBatch){
        viewport = new FitViewport(LibGDXGame.WIDTH,LibGDXGame.HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport, spriteBatch);

        table = new Table();
        table.top();
        table.setFillParent(true);

        mainLabel = new Label("YOU WON",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        secondaryLabel = new Label("PRESS ENTER TO START AGAIN" ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(mainLabel).padTop(50);
        table.row();
        table.add(secondaryLabel).padTop(15);

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
