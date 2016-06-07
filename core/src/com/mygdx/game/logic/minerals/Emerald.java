package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.PlayState;

/**
 * The Emerald class
 */
public class Emerald extends Mineral
{

    /**
     * Instantiates a new Mineral with the properties of Emerald
     * @param play_state current PlayState
     * @param map game map
     * @param object object from map
     */
    public Emerald(PlayState play_state,Map map, MapObject object)
    {
        super(play_state, map, object, 40, 5000, 25000);
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
