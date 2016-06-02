package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Buttons;
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
    Texture driller;
    Texture title;
    Texture play;
    Texture exit;

    Buttons playButton;

    public MenuScreen(MenuState menu_state, Motherload game)
    {
        this.menu_state = menu_state;
        this.game = game;
        play_state = new PlayState();
        playButton = new Buttons("play.png",353,108);

        background = new Texture("map.png");
        driller = new Texture("motherload_sprites/ground_right.png");
        title = new Texture("title.png");

        exit = new Texture("exit.png");

        menuCam = new OrthographicCamera();
        menuCam.setToOrtho(false, background.getWidth(),background.getHeight());
        menuPort = new FitViewport(background.getWidth(),background.getHeight()/3, menuCam);

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
        game.batch.draw(driller,background.getWidth()/2,background.getHeight()/3,driller.getWidth()*7,driller.getHeight()*7);
        game.batch.draw(title,background.getWidth()/9,background.getHeight()/1.8f+100,title.getWidth()*1.5f,title.getHeight()*1.5f);
        game.batch.draw(playButton.getButtonTex(),background.getWidth()/9,background.getHeight()/2);
        game.batch.draw(exit,background.getWidth()/9,background.getHeight()/2.2f);
        game.batch.end();

        if(playButton.Touched(playButton.getWidth(),playButton.getHeight()))
        {
            game.setScreen(new PlayScreen(play_state, game));
            dispose();
        }
       /* if (Gdx.input.isTouched())
        {

            game.setScreen(new PlayScreen(play_state, game));
            dispose();
        }*/
    }

    @Override
    public void resize(int width, int height) {
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
    public void dispose()
    {
        background.dispose();
    }
}
