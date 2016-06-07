package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * The Gas Station class
 */
public class GasStation
{
    public static final int FUEL_PRICE = 1;
    public static final int HEALTH_PRICE = 10;

    PlayState play_state;
    World world;

    /**
     * Instantiates a new Gas Station
     * @param play_state current playState
     * @param x x position of gas station
     * @param y y position of gas station
     */
    public GasStation(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        create(x,y);
    }

    /**
     * defines all the attributes of the gas station
     * @param x x position of gas station
     * @param y y position of gas station
     */
    private void create(int x, int y)
    {

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        MapProperties map_p = play_state.getMap().getMap().getProperties();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(x, map_p.get("height", Integer.class) + y);

        fdef.isSensor = true;
        body = world.createBody(bdef);
        shape.setAsBox(1, 1);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("gas_station");


    }

    /**
     * trades the player money for health and fuel
     * @param driller driller currently in the play State
     */
    public void shop(Driller driller)
    {
        int fuel = (int)Math.ceil(driller.getFuel());
        if(play_state.getMoney() < (FUEL_PRICE*(driller.getMax_fuel() - fuel)))
        {
            driller.setFuel(fuel + play_state.getMoney()/FUEL_PRICE);
            play_state.setMoney(0);
            return;
        }
        else
        {
            play_state.setMoney(play_state.getMoney() - (int)(FUEL_PRICE*(driller.getMax_fuel() - fuel)));
            driller.setFuel(driller.getMax_fuel());
        }


        if(play_state.getMoney() < (HEALTH_PRICE*(driller.getMax_health() - driller.getHealth())))
        {
            driller.setHealth(driller.getHealth() + play_state.getMoney()/HEALTH_PRICE);
            play_state.setMoney(0);
            return;
        }
        else
        {
            play_state.setMoney(play_state.getMoney() - (int)(HEALTH_PRICE*(driller.getMax_health() - driller.getHealth())));
            driller.setHealth(driller.getMax_health());
        }





    }
}
