package com.mygdx.game.gui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Driller;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 31/05/2016.
 */
public class PlayScreen implements Screen{


    PlayState play_state;
    Motherload game;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Box2DDebugRenderer b2dr;
    private Driller driller;
    private Hud hud;

    private Music music;



    public PlayScreen(PlayState play_state, Motherload game)
    {
        this.game = game;
        this.play_state = play_state;
        b2dr = new Box2DDebugRenderer();



        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(Motherload.V_WIDTH/ Motherload.PPM, Motherload.V_HEIGHT/ Motherload.PPM, gamecam);

        gamecam.position.set(gamePort.getWorldWidth()/2/Motherload.PPM, 1200/Motherload.PPM, 0);

        driller = play_state.getDriller();

        this.hud = new Hud(play_state, game.batch);

        if(Motherload.music) {
            this.music = Gdx.audio.newMusic(Gdx.files.internal("Motherload OST - Heavy Industry HQ.mp3"));
            music.setVolume(0.5f);
            music.setLooping(true);
            music.play();
        }

    }

    public void update(float delta_time) {


        play_state.getWorld().step(1/60f, 6, 2);

        gamecam.position.x = driller.b2body.getPosition().x;
        gamecam.position.y = driller.b2body.getPosition().y;

        //update driller texture
        driller.updateTexture(delta_time);

        //update driller status
        driller.updateMove(delta_time);
        driller.updateHealth();
        driller.updateFuel(delta_time/10);

        if(play_state.checkGameOver() == true)
            game.setScreen(new GameOverScreen(game, game.pState));



        gamecam.update();
        play_state.getMap().getRenderer().setView(gamecam);

        hud.updateHud();

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);
    }

    @Override
    public void render(float delta_time) {
        update(delta_time);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        play_state.getMap().getRenderer().render();


        if(Gdx.app.getType() == Application.ApplicationType.Desktop)
            b2dr.render(play_state.getWorld(), gamecam.combined);

        gamecam.update();

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        driller.draw(game.batch);
        game.batch.end();

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();



    }

    public void endGame()
    {
        game.setScreen(new GameOverScreen(game, play_state));
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
        hud.updateViewport(width, height);
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
        music.dispose();
    }
}
