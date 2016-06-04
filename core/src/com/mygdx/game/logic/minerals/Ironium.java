package com.mygdx.game.logic.minerals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.Map;
import com.mygdx.game.logic.Mineral;
import com.mygdx.game.logic.MineralType;
import com.mygdx.game.logic.PlayState;

/**
 * Created by Rita on 03/06/2016.
 */
public class Ironium extends Mineral
{
    public Ironium(PlayState play_state, Map map, MapObject object)
    {
        super(play_state, map, object, 10, 30, 150, MineralType.IRONIUM);
        fixture.setUserData(this);
        setCategoryFilter(Motherload.MINERAL_BIT);
    }

    @Override
    public void drill() {
        Gdx.app.log("Ironium", "Collision");

        setCategoryFilter(Motherload.DESTROYED_BIT);
        getCell().setTile(null);

        play_state.score = play_state.score + points;
        System.out.print("Score: ");
        System.out.print(play_state.score);
        System.out.print("\n");

        play_state.getDriller().addMineral(this);
    }
}
