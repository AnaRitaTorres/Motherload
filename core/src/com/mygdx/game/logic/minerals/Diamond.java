package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Rita on 03/06/2016.
 */
public class Diamond  extends Mineral
{
    public Diamond(PlayState play_state, MapObject object)
    {
        super(play_state, object,80,20000, 0, MineralType.DIAMOND);
    }

    @Override
    public void drill() {

    }
}
