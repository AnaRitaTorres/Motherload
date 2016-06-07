package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Rita on 03/06/2016.
 */
public class Goldium extends Mineral
{
    public Goldium(PlayState play_state,Map map, MapObject object)
    {
        super(play_state, map, object, 20, 250,1250);
    }

    @Override
    public void drill() {

    }
}
