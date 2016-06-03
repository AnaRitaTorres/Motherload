package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Rita on 03/06/2016.
 */
public class Einsteinium extends Mineral
{
    public Einsteinium(Map map, MapObject object)
    {
        super(map, object, 40, 2000, 10000, MineralType.EINSTEINIUM);
    }

    @Override
    public void drill() {

    }
}
