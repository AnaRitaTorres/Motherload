package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.gui.MenuScreen;
import com.mygdx.game.gui.PlayScreen;
import com.mygdx.game.logic.MenuState;
import com.mygdx.game.logic.PlayState;

public class Motherload extends Game
{
	public static final int V_WIDTH = 400/2;
	public static final int V_HEIGHT = 500/2;
	public static final float PPM = 16;
	public SpriteBatch batch;
	public PlayState pState;
	public MenuState mState;

	@Override
	public void create ()
	{
		batch = new SpriteBatch();
		pState = new PlayState();
		setScreen(new MenuScreen(mState, this));
	}

	@Override
	public void render ()
	{
		super.render();
	}
}
