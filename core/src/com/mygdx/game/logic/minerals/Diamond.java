package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Rita on 03/06/2016.
 */
public class Diamond  extends Mineral
{
    public Diamond(Map map, MapObject object)
    {
        super(map, object,80,20000, 0, MineralType.DIAMOND);
    }

    @Override
    public void drill() {

    }
}
