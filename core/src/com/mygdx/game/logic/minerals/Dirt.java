package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;

/**
 * Created by Daniel on 02/06/2016.
 */
public class Dirt extends Mineral {

    public Dirt(Map map, MapObject object) {
        super(map, object, 1, 0, 25, MineralType.DIRT);
        fixture.setUserData(this);
        setCatgoryFilter(Motherload.MINERAL_BIT);
    }

    @Override
    public void drill() {
        Gdx.app.log("Dirt", "Collision");


        setCatgoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);

    }
}
