package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Driller;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 04/06/2016.
 */
public class Hud {

    public Stage stage;
    private Viewport viewport;
    private PlayState play_state;
    private Driller driller;

    Label fuelLabel;
    Label healthLabel;
    Label scoreLabel;

    public Hud(PlayState play_state, SpriteBatch sb){

        this.play_state = play_state;
        this.driller = play_state.getDriller();

        viewport  = new FitViewport(Motherload.V_WIDTH*2, Motherload.V_HEIGHT*2, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        fuelLabel = new Label(String.format("fuel: %d/%d", (int)driller.getFuel(), driller.getMax_fuel()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label(String.format("health: %d/%d", driller.getHealth(), driller.getMax_health()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("score: %09d", play_state.getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));


        table.add(fuelLabel).expandX().padTop(10);
        table.add(healthLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table);
    }

    public void updateHud()
    {
        scoreLabel.setText(String.format("score: %09d", play_state.getScore()));
        healthLabel.setText(String.format("health: %d/%d", driller.getHealth(), driller.getMax_health()));
        fuelLabel.setText(String.format("fuel: %d/%d", (int)driller.getFuel(), driller.getMax_fuel()));
    }
}
