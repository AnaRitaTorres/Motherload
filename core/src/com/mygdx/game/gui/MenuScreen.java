package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.MenuState;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Rita on 01/06/2016.
 */
public class MenuScreen implements Screen
{
    MenuState menu_state;
    Motherload game;

    private OrthographicCamera menuCam;
    private Viewport menuPort;

    PlayState play_state;

    Texture background;

    public MenuScreen(MenuState menu_state, Motherload game)
    {
        this.menu_state = menu_state;
        this.game = game;
        play_state = new PlayState();

        background = new Texture("badlogic.jpg");

        menuCam = new OrthographicCamera();
        menuCam.setToOrtho(false, Motherload.V_WIDTH/ Motherload.PPM,  Motherload.V_HEIGHT/ Motherload.PPM);
        menuPort = new FitViewport(Motherload.V_WIDTH/ Motherload.PPM, Motherload.V_HEIGHT/ Motherload.PPM, menuCam);

       // menuCam = new OrthographicCamera();

       // menuPort = new FitViewport(1920,1030, menuCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);

        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.end();

        if (Gdx.input.isTouched())
        {
            game.setScreen(new PlayScreen(play_state, game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height)
    {
        menuPort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume()
        {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose()
    {
        background.dispose();
    }
}
