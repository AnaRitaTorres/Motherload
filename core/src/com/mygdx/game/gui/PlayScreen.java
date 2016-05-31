package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 31/05/2016.
 */
public class PlayScreen implements Screen{

    PlayState play_state;
    Motherload game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;



    public PlayScreen(PlayState play_state, Motherload game)
    {
        this.game = game;
        this.play_state = play_state;

        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Motherload.V_WIDTH/ Motherload.PPM, Motherload.V_HEIGHT/ Motherload.PPM, gamecam);
    }

    public void update(float delta_time) {
        play_state.getWorld().step(1/60f, 6, 2);
        gamecam.update();
        play_state.getMap().getRenderer().setView(gamecam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta_time) {
        update(delta_time);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        play_state.getMap().getRenderer().render();

        play_state.getMap().getB2dr().render(play_state.getWorld(), gamecam.combined);

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
