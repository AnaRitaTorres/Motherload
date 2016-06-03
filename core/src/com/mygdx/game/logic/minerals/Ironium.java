package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Rita on 03/06/2016.
 */
public class Ironium extends Mineral
{
    public Ironium(Map map, MapObject object)
    {
        super(map, object, 10, 30, 150, MineralType.IRONIUM);
    }

    @Override
    public void drill() {

    }
}
