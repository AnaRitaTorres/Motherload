package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Rita on 03/06/2016.
 */
public class Emerald extends Mineral
{

    public Emerald(Map map, MapObject object)
    {
        super(map, object, 40, 5000, 25000, MineralType.EMERALD);
    }

    @Override
    public void drill() {

    }
}
