package com.mygdx.game.gui;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.MenuState;

/**
 * Created by Rita on 01/06/2016.
 */
public class MenuScreen implements Screen
{
    MenuState menu_state;
    Motherload game;

    private OrthographicCamera menuCam;
    private Viewport menuPort;

    public MenuScreen(MenuState menu_state, Motherload game)
    {
        this.menu_state = menu_state;
        this.game = game;

        menuCam = new OrthographicCamera();
        menuPort = new FitViewport(Motherload.V_WIDTH/ Motherload.PPM, Motherload.V_HEIGHT/ Motherload.PPM, menuCam);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
