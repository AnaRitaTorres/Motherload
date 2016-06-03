package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Rita on 03/06/2016.
 */
public class Platinium extends Mineral
{
    public Platinium(Map map, MapObject object)
    {
        super(map, object, 30, 750, 3750, MineralType.PLATINIUM);
    }

    @Override
    public void drill() {

    }
}
