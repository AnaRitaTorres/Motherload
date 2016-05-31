package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
    private Box2DDebugRenderer b2dr;

    Texture texture;

    public PlayScreen(PlayState play_state, Motherload game)
    {
        this.game = game;
        this.play_state = play_state;
        b2dr = new Box2DDebugRenderer();


        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Motherload.V_WIDTH/ Motherload.PPM, Motherload.V_HEIGHT/ Motherload.PPM, gamecam);
        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
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
        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        play_state.getMap().getRenderer().render();

        b2dr.render(play_state.getWorld(), gamecam.combined);
        gamecam.update();
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
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
