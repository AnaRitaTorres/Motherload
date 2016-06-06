package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Daniel on 06/06/2016.
 */
public class UpgradeStore {

    private int hull_upgrade_cost = 100;
    private int fuel_upgrade_cost = 100;
    private int cargo_upgrade_cost = 100;

    private PlayState play_state;
    private World world;

    public UpgradeStore(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        defineUpgradeStore(x, y);
    }

    public void defineUpgradeStore(int x, int y)
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
        body.createFixture(fdef).setUserData("upgrade_store");
    }

    public void buyHullUpgrade()
    {
        if(play_state.getMoney() >= hull_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() -  hull_upgrade_cost);
            hull_upgrade_cost += 100;
            play_state.getDriller().addMax_health(10);

        }
    }

    public void buyCargoUpgrades()
    {
        if(play_state.getMoney() >= cargo_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() - cargo_upgrade_cost);
            cargo_upgrade_cost += 100;
            play_state.getDriller().addCapacity(5);
        }
    }

    public void buyFuelUpgrades()
    {
        if(play_state.getMoney() >= fuel_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() - fuel_upgrade_cost);
            fuel_upgrade_cost += 100;
            play_state.getDriller().addMax_fuel(10);
        }
    }

    public int getHull_upgrade_cost() {
        return hull_upgrade_cost;
    }

    public int getCargo_upgrade_cost() {
        return cargo_upgrade_cost;
    }

    public int getFuel_upgrade_cost() {
        return fuel_upgrade_cost;
    }
}
