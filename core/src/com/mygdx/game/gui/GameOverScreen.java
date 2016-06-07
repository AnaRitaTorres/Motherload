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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 07/06/2016.
 */
public class GameOverScreen implements Screen{

    public static final int BUTTON_WIDTH = 120;
    public static final int BUTTON_HEIGHT = 60;

    private Motherload game;
    private PlayState play_state;

    private OrthographicCamera overCam;
    private Viewport overPort;

    private TextButton return_button;

    private Label score_label;
    private Label money_label;

    private Label.LabelStyle label_style;
    private Texture texture_solid;
    private Texture game_over_text;

    private Stage stage;
    private Skin skin;

    public GameOverScreen(Motherload game, PlayState play_state)
    {
        this.game = game;
        this.play_state = play_state;
        create();
        handleInput();
    }


    private void create()
    {
        overCam = new  OrthographicCamera();
        overPort = new FitViewport(Motherload.V_WIDTH*2, Motherload.V_WIDTH*2, overCam);
        overPort.apply();

        stage = new Stage(overPort, game.batch);
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

        //button
        return_button = new TextButton("MAIN MENU", textButtonStyle);
        return_button.setPosition(140, 120);
        stage.addActor(return_button);

        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(0x556B2FFF);
        pix.fill();
        texture_solid = new Texture(pix);

        label_style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        label_style.background = new TextureRegionDrawable(new TextureRegion(texture_solid));

        score_label = new Label(String.format(" Final Score:   %09d ", play_state.score), label_style);
        score_label.setPosition(115, 240);
        stage.addActor(score_label);

        score_label = new Label(String.format(" Final Money:  $%08d ", play_state.getMoney()), label_style);
        score_label.setPosition(115, 290);
        stage.addActor(score_label);

        game_over_text = new Texture("game_over.png");
        TextureRegion region = new TextureRegion(game_over_text, 0, 0, 300, 43);
        Image actor = new Image(region);
        actor.setPosition(50, 360);
        stage.addActor(actor);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        overCam.update();
        game.batch.setProjectionMatrix(overCam.combined);


        game.batch.begin();
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        overPort.update(width,height);
        overCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);
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
        return_button.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                game.pState = new PlayState(game);
                game.setScreen(new MenuScreen(game));

            }
        });

    }
}
