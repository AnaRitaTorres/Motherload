package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.PlayState;

/**
 * The Bronzium class
 */
public class Bronzium extends Mineral
{
    /**
     * Instantiates a new Mineral with the properties of Bronzium
     * @param play_state current PlayState
     * @param map game map
     * @param object object from map
     */
    public Bronzium(PlayState play_state, Map map, MapObject object) {
        super(play_state, map, object, 10, 30, 150);
        fixture.setUserData(this);
        setCategoryFilter(Motherload.MINERAL_BIT);
    }

    @Override
    public void drill() {

        setCategoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);

        play_state.score = play_state.score + points;

        play_state.getDriller().addMineral(this);

    }
}
