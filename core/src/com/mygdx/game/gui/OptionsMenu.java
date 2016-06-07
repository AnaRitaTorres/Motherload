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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 07/06/2016.
 */
public class OptionsMenu implements Screen {

    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 40;

    private Motherload game;
    private PlayState play_state;

    private OrthographicCamera optionsCam;
    private Viewport optionsPort;

    private TextButton return_button;
    private TextButton music_button;

    private Texture options_text;

    private Stage stage;
    private Skin skin;

    private Music music;

    public OptionsMenu(Motherload game, PlayState play_state)
    {
        this.game = game;
        this.play_state = play_state;
        create();
        handleInput();
    }

    public void create()
    {
        optionsCam = new OrthographicCamera();
        optionsPort = new FitViewport(Motherload.V_WIDTH, Motherload.V_HEIGHT, optionsCam);
        optionsPort.apply();

        stage = new Stage(optionsPort, game.batch);
        Gdx.input.setInputProcessor(stage);

        skin = new Skin();

        Pixmap pixmap = new Pixmap(BUTTON_WIDTH, BUTTON_HEIGHT, Pixmap.Format.RGB888);
        pixmap.setColor(Color.CHARTREUSE);
        pixmap.fill();

        skin.add("click", new Texture(pixmap));

        BitmapFont bfont=new BitmapFont();
        bfont.getData().setScale(0.7f);
        skin.add("default",bfont);

        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("click", Color.DARK_GRAY);
        textButtonStyle.over = skin.newDrawable("click", Color.LIGHT_GRAY);

        textButtonStyle.font = skin.getFont("default");

        skin.add("default", textButtonStyle);

        if(Motherload.music)
            music_button = new TextButton("MUSIC ON",textButtonStyle);
        else
            music_button = new TextButton("MUSIC OFF",textButtonStyle);

        music_button.setPosition(140, 230);
        stage.addActor(music_button);

        return_button = new TextButton("RETURN",textButtonStyle);
        return_button.setPosition(140, 170);
        stage.addActor(return_button);

        options_text = new Texture("options.png");
        TextureRegion region = new TextureRegion(options_text, 0, 0, 177, 34);
        Image actor = new Image(region);
        actor.setPosition(111, 300);
        stage.addActor(actor);


        music = Gdx.audio.newMusic(Gdx.files.internal("Rick Astley - Never Gonna Give You Up.mp3"));
        music.setVolume(0.5f);
        music.setLooping(true);
        music.play();


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        if(!Motherload.music && music != null)
            music.pause();

        if(Motherload.music && music != null)
            music.play();

        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        optionsCam.update();
        game.batch.setProjectionMatrix(optionsCam.combined);


        game.batch.begin();
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        optionsPort.update(width,height);
        optionsCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);
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
    public void dispose() {

    }

    public void handleInput()
    {
        music_button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor) {

                if (Motherload.music == true){
                    Motherload.music = false;
                    music_button.setText("MUSIC OFF");
                }
                else {
                    Motherload.music = true;
                    music_button.setText("MUSIC ON");
                }
            }
        });

        return_button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}
