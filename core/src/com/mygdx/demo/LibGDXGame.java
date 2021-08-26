package com.mygdx.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.demo.screens.FirsLevelScreen;
public class LibGDXGame extends Game {

	public SpriteBatch batch;
	Texture img;
	public final static int WIDTH = 400;
	public final static int HEIGHT = 208;
	public final static float PPM = 100;

	public static final short DEFAULT_BIT = 1;
	public static final short FOX_BIT = 2;
	public static final short CHERRY_BIT = 4;
	public static final short CONTACT_BIT = 8;
	public static final short ENEMY_BIT = 16;

	@Override
	public void create () {
		batch = new SpriteBatch();
		this.setScreen(new FirsLevelScreen(this));
	}
}
