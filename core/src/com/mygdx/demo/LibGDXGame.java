package com.mygdx.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.demo.screens.MenuScreen;

public class LibGDXGame extends Game {
	//public SpriteBatch batch;
	Texture img;
	public final static int WIDTH = 800;
	public final static int HEIGHT = 600;

	private boolean paused;
	
	@Override
	public void create () {
		//batch = new SpriteBatch();
		this.setScreen(new MenuScreen(this));
	}

	/*
	GETTERS AND SETTERS BELOW
	*///

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}
}
