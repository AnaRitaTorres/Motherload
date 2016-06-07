package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import com.mygdx.game.logic.UpgradeStore;

/**
 * Created by Daniel on 07/06/2016.
 */
public class PauseScreen  implements Screen {

    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 40;

    private Motherload game;
    private PlayState play_state;

    private OrthographicCamera pauseCam;
    private Viewport pausePort;

    private TextButton resume_button;
    private TextButton exit_button;

    private Texture pause_text;

    private Stage stage;
    private Skin skin;


    public PauseScreen(Motherload game, PlayState play_state)
    {
        this.play_state = play_state;
        this.game = game;
        create();
        handleInput();
    }

    public void create()
    {
        pauseCam = new OrthographicCamera();
        pausePort = new FitViewport(Motherload.V_WIDTH*2, Motherload.V_HEIGHT, pauseCam);
        pausePort.apply();

        stage = new Stage(pausePort, game.batch);
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

        resume_button = new TextButton("RESUME",textButtonStyle);
        resume_button.setPosition(140, 230);
        stage.addActor(resume_button);

        exit_button = new TextButton("EXIT",textButtonStyle);
        exit_button.setPosition(140, 170);
        stage.addActor(exit_button);

        pause_text = new Texture("pause.png");
        TextureRegion region = new TextureRegion(pause_text, 0, 0, 163, 43);
        Image actor = new Image(region);
        actor.setPosition(118, 300);
        stage.addActor(actor);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        pauseCam.update();
        game.batch.setProjectionMatrix(pauseCam.combined);


        game.batch.begin();
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        pausePort.update(width,height);
        pauseCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);
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

    public void handleInput()
    {
        resume_button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                System.out.println("RESUME pause");
                game.setScreen(new PlayScreen(play_state,game));

            }
        });

        exit_button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                System.out.println("EXIT pause");
                game.pState = new PlayState(game);
                game.setScreen(new MenuScreen(game));


            }
        });
    }
}
