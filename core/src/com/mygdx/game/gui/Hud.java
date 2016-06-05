package com.mygdx.game.gui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
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
    private Texture texture_solid;
    private Label.LabelStyle label_style;

    Label fuelLabel;
    Label healthLabel;
    Label scoreLabel;
    Label capacityLabel;
    Label moneyLabel;

    public Hud(PlayState play_state, SpriteBatch sb){

        this.play_state = play_state;
        this.driller = play_state.getDriller();

        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(0x000000FF);
        pix.fill();
        texture_solid = new Texture(pix);

        label_style = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        label_style.background = new TextureRegionDrawable(new TextureRegion(texture_solid));

        viewport  = new FitViewport(Motherload.V_WIDTH*2, Motherload.V_HEIGHT*2, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        fuelLabel = new Label(String.format("Fuel: %d/%d", (int)driller.getFuel(), driller.getMax_fuel()), label_style);
        healthLabel = new Label(String.format("Health: %d/%d", driller.getHealth(), driller.getMax_health()), label_style);
        scoreLabel = new Label(String.format("Score: %09d", play_state.getScore()), label_style);
        capacityLabel = new Label(String.format("Capacity: %d/%d", driller.getMinerals().size(), driller.getCapacity()), label_style);
        moneyLabel = new Label(String.format("Money: %07d", play_state.getMoney()), label_style);

        table.add(fuelLabel).expandX().padTop(10).align(Align.left);
        table.add((Label)null);
        table.add(scoreLabel).expandX().padTop(10).align(Align.right);
        table.row();
        table.add(healthLabel).expandX().padTop(10).align(Align.left);
        table.add((Label)null);
        table.add(moneyLabel).expandX().padTop(10).align(Align.right);
        table.row();
        table.add(capacityLabel).expandX().padTop(10).align(Align.left);
        table.add((Label)null);



        stage.addActor(table);
    }

    public void updateHud()
    {
        scoreLabel.setText(String.format(" Score: %09d ", play_state.getScore()));
        healthLabel.setText(String.format(" Health: %d/%d ", driller.getHealth(), driller.getMax_health()));
        fuelLabel.setText(String.format(" Fuel: %d/%d ", (int)Math.ceil(driller.getFuel()), driller.getMax_fuel()));
        capacityLabel.setText(String.format(" Capacity: %d/%d ", driller.getMinerals().size(), driller.getCapacity()));
        moneyLabel.setText(String.format(" Money: %07d ", play_state.getMoney(), label_style));
    }
}
