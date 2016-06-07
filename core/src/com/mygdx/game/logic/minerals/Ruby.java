package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.PlayState;

/**
 * The Ruby class
 */
public class Ruby extends Mineral
{
    /**
     * Instantiates a new Mineral with the properties of Ruby
     * @param play_state current PlayState
     * @param map game map
     * @param object object from map
     */
    public Ruby(PlayState play_state, Map map, MapObject object)
    {
        super(play_state, map, object,80, 20000,0);
        fixture.setUserData(this);
        setCategoryFilter(Motherload.MINERAL_BIT);
    }

    @Override
    public void drill()
    {
        setCategoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);

        play_state.score = play_state.score + points;

        play_state.getDriller().addMineral(this);
    }
}
