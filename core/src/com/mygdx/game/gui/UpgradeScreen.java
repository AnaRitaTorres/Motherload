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
 * Created by Daniel on 06/06/2016.
 */
public class UpgradeScreen implements Screen {

    private static final int BUTTON_WIDTH = 120;
    private static final int BUTTON_HEIGHT = 60;

    Motherload game;
    PlayState play_state;

    private OrthographicCamera storeCam;
    private Viewport storePort;

    TextButton upgradeHull;
    TextButton upgradeFuelTank;
    TextButton upgradeContainer;
    TextButton exit;

    Label hull_label;
    Label cargo_label;
    Label fuel_label;
    private Label.LabelStyle label_style;
    private Texture texture_solid;

    private int hull_upgrade_cost = 100;
    private int fuel_upgrade_cost = 100;
    private int cargo_upgrade_cost = 100;

    Stage stage;
    Skin skin;

    Hud hud;

    private Music music;

    public UpgradeScreen(Motherload game, PlayState play_state) {
        this.game = game;
        this.play_state = play_state;
        this.hud = new Hud(play_state, game.batch);
        create();
        handleInput();

    }


    public void create()
    {
        storeCam = new OrthographicCamera();
        storePort = new FitViewport(Motherload.V_WIDTH*2, Motherload.V_HEIGHT*2, storeCam);
        storePort.apply();

        stage = new Stage(storePort, game.batch);
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

        //Define title button
        upgradeHull = new TextButton("UPGRADE HULL",textButtonStyle);
        upgradeHull.setPosition(50, 300);
        stage.addActor(upgradeHull);

        upgradeFuelTank = new TextButton("UPGRADE TANK",textButtonStyle);
        upgradeFuelTank.setPosition(230,300);
        stage.addActor(upgradeFuelTank);

        upgradeContainer = new TextButton("UPGRADE CARGO BAY",textButtonStyle);
        upgradeContainer.setPosition(50, 170);
        stage.addActor(upgradeContainer);

        exit = new TextButton("EXIT",textButtonStyle);
        exit.setPosition(230, 170);
        stage.addActor(exit);


        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(0x556B2FFF);
        pix.fill();
        texture_solid = new Texture(pix);

        label_style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        label_style.background = new TextureRegionDrawable(new TextureRegion(texture_solid));

        fuel_label = new Label(" Cost: 100 ", label_style);
        fuel_label.setPosition(230,270);
        stage.addActor(fuel_label);

        hull_label = new Label(" Cost: 100 ", label_style);
        hull_label.setPosition(50,270);
        stage.addActor(hull_label);

        cargo_label = new Label(" Cost: 100 ", label_style);
        cargo_label.setPosition(50,140);
        stage.addActor(cargo_label);

        updateCosts();


    }

    public void updateCosts()
    {
        cargo_label.setText(String.format(" Cost: $%d ", cargo_upgrade_cost));
        hull_label.setText(String.format(" Cost: $%d ", hull_upgrade_cost));
        fuel_label.setText(String.format(" Cost: $%d ", fuel_upgrade_cost));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        storeCam.update();
        game.batch.setProjectionMatrix(storeCam.combined);


        game.batch.begin();
        game.batch.end();

        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

        hud.updateHud();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        storePort.update(width,height);
        storeCam.position.set(game.V_WIDTH,game.V_HEIGHT,0);
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
        stage.dispose();
        skin.dispose();
        music.dispose();
    }

    public void handleInput()
    {

        upgradeHull.addListener(new ChangeListener()
        {
            public void changed (ChangeEvent event, Actor actor)
            {
                if(play_state.getMoney() >= hull_upgrade_cost) {
                    play_state.setMoney(play_state.getMoney() -  hull_upgrade_cost);
                    hull_upgrade_cost += 100;
                    play_state.getDriller().addMax_health(10);
                    updateCosts();
                }
            }
        });

       upgradeContainer.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if(play_state.getMoney() >= cargo_upgrade_cost) {
                    play_state.setMoney(play_state.getMoney() - cargo_upgrade_cost);
                    cargo_upgrade_cost += 100;
                    play_state.getDriller().addCapacity(5);
                    updateCosts();
                }
            }
        });

        upgradeFuelTank.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                if(play_state.getMoney() >= fuel_upgrade_cost) {
                    play_state.setMoney(play_state.getMoney() - fuel_upgrade_cost);
                    fuel_upgrade_cost += 100;
                    play_state.getDriller().addMax_fuel(10);
                    updateCosts();
                }
            }
        });

        exit.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }
}
