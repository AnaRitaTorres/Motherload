package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Daniel on 02/06/2016.
 */
public class Dirt extends Mineral {

    public Dirt(PlayState play_state, MapObject object) {
        super(play_state, object, 1, 0, 25, MineralType.DIRT);
        fixture.setUserData(this);
        setCategoryFilter(Motherload.MINERAL_BIT);
    }

    @Override
    public void drill() {
        Gdx.app.log("Dirt", "Collision");



        setCategoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);

        play_state.score = play_state.score + points;
        System.out.print("Score: ");
        System.out.print(play_state.score);
        System.out.print("\n");
    }
}
