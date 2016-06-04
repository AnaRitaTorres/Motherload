package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Buttons;
import com.mygdx.game.logic.MenuState;
import com.mygdx.game.logic.PlayState;


/**
 * Created by Rita on 01/06/2016.
 */
public class MenuScreen implements Screen,InputHandler
{
    MenuState menu_state;
    Motherload game;

    private OrthographicCamera menuCam;
    private Viewport menuPort;

    PlayState play_state;

    Texture background;
    Texture driller;
    Texture title;

    Buttons playButton,exitButton;

    Rectangle r = new Rectangle();
    Rectangle r1 = new Rectangle();

    public MenuScreen(MenuState menu_state, Motherload game)
    {
        this.menu_state = menu_state;
        this.game = game;
        play_state = new PlayState();
        playButton = new Buttons("play.png",40,230);
        exitButton = new Buttons ("exit.png",40,130);

        r.set(playButton.getCoordinates().x,playButton.getCoordinates().y,playButton.getWidth(),playButton.getHeight());
        r1.set(exitButton.getCoordinates().x,exitButton.getCoordinates().y,exitButton.getWidth(),exitButton.getHeight());

        background = new Texture("map2.png");
        driller = new Texture("motherload_sprites/ground_right.png");
        title = new Texture("titulo.png");

        menuCam = new OrthographicCamera();
        menuCam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        menuPort = new FillViewport(Gdx.graphics.getWidth(),Gdx.graphics.getHeight(), menuCam);
        menuPort.apply(true);

    }

    public void update(float delta_time)
    {
        Vector2 v = inputHandler();
        Vector3 b = new Vector3(v.x,v.y,0);


        System.out.print(r.getX() + " " + r.getY() + " " + r.getWidth() + "  " + r.getHeight() + "     ");

        if (r.contains(b.x,b.y))
        {
            Gdx.app.log("Mouse Clicked", "");
            game.setScreen(new PlayScreen(play_state, game));
            dispose();

        }

    }
    public Vector2 inputHandler()
    {
        Vector2 v = new Vector2();

        if (Gdx.input.isTouched())
        {
            v.set(Gdx.input.getX(),Gdx.input.getY());
            Vector3 b = new Vector3(v.x,v.y,0);
            menuCam.unproject(b);
        }
        else v = new Vector2(-1000,-1000);
        return v;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta_time)
    {
        update(delta_time);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        menuCam.update();
        game.batch.setProjectionMatrix(menuCam.combined);
        game.batch.begin();
        game.batch.draw(background, 0, 0);
        game.batch.draw(driller,400,0,driller.getWidth()*2,driller.getHeight()*2);
        game.batch.draw(title,40,370,title.getWidth()/1.5f,title.getHeight()/1.5f);
        game.batch.draw(playButton.getButtonTex(),playButton.getCoordinates().x,playButton.getCoordinates().y, playButton.getWidth()/1.8f,playButton.getHeight()/1.8f);
        game.batch.draw(exitButton.getButtonTex(),exitButton.getCoordinates().x,exitButton.getCoordinates().y, exitButton.getWidth()/1.8f,exitButton.getHeight()/1.8f);
        game.batch.end();

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
