package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
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

    private static final int BUTTON_WIDTH = 250;
    private static final int BUTTON_HEIGHT = 50;


    MenuState menu_state;
    Motherload game;

    private OrthographicCamera menuCam;
    private Viewport menuPort;

    PlayState play_state;

    Stage stage;
    Skin skin;

    Texture background;
    Texture driller;

    TextButton titleButton;
    TextButton playButton;
    TextButton optionsButton;
    TextButton exitButton;

     public MenuScreen(MenuState menu_state, Motherload game)
    {
        this.menu_state = menu_state;
        this.game = game;
        play_state = new PlayState();
        create();
        handleInput();

        background = new Texture("map.png");
        driller = new Texture("motherload_sprites/ground_right.png");
    }

    public void create()
    {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(BUTTON_WIDTH, BUTTON_HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();

        skin.add("white", new Texture(pixmap));

        BitmapFont bfont=new BitmapFont();
        skin.add("default",bfont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
        textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        //Define title button
        titleButton = new TextButton("MOTHERLOAD",textButtonStyle);
        titleButton.setPosition(180,400);
        stage.addActor(titleButton);

        //Define play button
        playButton = new TextButton("PLAY",textButtonStyle);
        playButton.setPosition(40, 300);
        stage.addActor(playButton);

        //Define options Button
        optionsButton = new TextButton("OPTIONS",textButtonStyle);
        optionsButton.setPosition(40, 200);
        stage.addActor(optionsButton);

        //Define Exit button
        exitButton = new TextButton("EXIT",textButtonStyle);
        exitButton.setPosition(40, 100);
        stage.addActor(exitButton);

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta_time)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(driller,250,65,driller.getWidth()*4,driller.getHeight()*4);
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height)
    {

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
        stage.dispose();
        skin.dispose();
    }


    public void handleInput()
    {

        playButton.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                game.setScreen(new PlayScreen(play_state, game));
            }
        });

        optionsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                //TODO options screen
            }
        });

         exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
}
