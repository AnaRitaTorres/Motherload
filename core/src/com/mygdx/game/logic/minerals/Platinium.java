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
    public Platinium(Map map, MapObject object, int weight, int value, int points, MineralType mineral_type) {
        super(map, object, weight, value, points, mineral_type);
    }

    @Override
    public void drill() {

    }
}
