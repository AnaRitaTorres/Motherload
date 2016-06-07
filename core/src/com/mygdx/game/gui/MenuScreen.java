package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;



/**
 * Created by Rita on 01/06/2016.
 */
public class MenuScreen implements Screen
{

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 80;


    Motherload game;

    private OrthographicCamera menuCam;
    private Viewport menuPort;

    Sprite b;
    Stage stage;
    Skin skin;

    TextButton titleButton;
    TextButton playButton;
    TextButton optionsButton;
    TextButton exitButton;

    private Music music;

    public MenuScreen(Motherload game)
    {
        this.game = game;
        create();
        handleInput();
    }

    public void create()
    {
        b = new Sprite(new Texture("map.png"));
        b.setPosition(0,0);
        b.setSize(game.V_WIDTH*2,game.V_HEIGHT*2);

        float aspectRatio = (float)Gdx.graphics.getHeight()/(float)Gdx.graphics.getWidth();

        menuCam  = new OrthographicCamera();
        menuPort = new FitViewport(game.V_WIDTH *2 ,game.V_HEIGHT*2,menuCam);
        menuPort.apply();
        menuCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);

        stage = new Stage(menuPort,game.batch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(BUTTON_WIDTH, BUTTON_HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.CHARTREUSE);
        pixmap.fill();

        skin.add("click", new Texture(pixmap));

        BitmapFont bfont=new BitmapFont();
        skin.add("default",bfont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("click", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("click", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        //Define title button
        titleButton = new TextButton("MOTHERLOAD",textButtonStyle);
        titleButton.setPosition(100,420);
        stage.addActor(titleButton);

        //Define play button
        playButton = new TextButton("PLAY",textButtonStyle);
        playButton.setPosition(100, 280);
        stage.addActor(playButton);

        //Define options Button
        optionsButton = new TextButton("OPTIONS",textButtonStyle);
        optionsButton.setPosition(100,140);
        stage.addActor(optionsButton);

        //Define Exit button
        exitButton = new TextButton("EXIT",textButtonStyle);
        exitButton.setPosition(100,0);
        stage.addActor(exitButton);

        if(Motherload.music) {
            this.music = Gdx.audio.newMusic(Gdx.files.internal("Darude - Sandstorm.mp3"));
            music.setVolume(0.5f);
            music.setLooping(true);
            music.play();
        }

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta_time)
    {

        Gdx.gl.glClearColor(102f/255f,51f/255f,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

       menuCam.update();
       game.batch.setProjectionMatrix(menuCam.combined);


        game.batch.begin();
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height)
    {
        menuPort.update(width,height);
        menuCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        if(music != null)
            music.stop();
    }

    @Override
    public void dispose()
    {
        stage.dispose();
        skin.dispose();
        music.dispose();
    }


    public void handleInput()
    {

        playButton.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                game.setScreen(new PlayScreen(game.pState, game));
            }
        });

        optionsButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {

                game.setScreen(new OptionsMenu(game, game.pState));

            }
        });

         exitButton.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });
    }
}
