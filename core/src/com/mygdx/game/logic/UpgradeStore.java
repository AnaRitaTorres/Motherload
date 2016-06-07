package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * The Upgrade Store class
 */
public class UpgradeStore {

    private int hull_upgrade_cost = 100;
    private int fuel_upgrade_cost = 100;
    private int cargo_upgrade_cost = 100;

    private PlayState play_state;
    private World world;

    /**
     * Instantiates a new Upgrade Store
     * @param play_state current PlayState
     * @param x x position of upgrade store
     * @param y y position of upgrade store
     */
    public UpgradeStore(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        defineUpgradeStore(x, y);
    }

    /**
     * defines all the attributes of the upgrade store
     * @param x x position of upgrade store
     * @param y y position of upgrade store
     */
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

    /**
     * trades player money for an upgrade to the hull(more health)
     */
    public void buyHullUpgrade()
    {
        if(play_state.getMoney() >= hull_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() -  hull_upgrade_cost);
            hull_upgrade_cost += 100;
            play_state.getDriller().addMax_health(10);

        }
    }

    /**
     * trades player money for an upgrade to the cargo bay (more capacity)
     */
    public void buyCargoUpgrades()
    {
        if(play_state.getMoney() >= cargo_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() - cargo_upgrade_cost);
            cargo_upgrade_cost += 100;
            play_state.getDriller().addCapacity(5);
        }
    }

    /**
     * trades player money for an upgrade to the fuel tank (more fuel capacity)
     */
    public void buyFuelUpgrades()
    {
        if(play_state.getMoney() >= fuel_upgrade_cost) {
            play_state.setMoney(play_state.getMoney() - fuel_upgrade_cost);
            fuel_upgrade_cost += 100;
            play_state.getDriller().addMax_fuel(10);
        }
    }

    /**
     * return hull upgrade cost
     * @return hull upgrade cost
     */
    public int getHull_upgrade_cost() {
        return hull_upgrade_cost;
    }

    /**
     * return cargo upgrade cost
     * @return cargo upgrade cost
     */
    public int getCargo_upgrade_cost() {
        return cargo_upgrade_cost;
    }

    /**
     * return fuel tank upgrade cost
     * @return fuel tank upgrade cost
     */
    public int getFuel_upgrade_cost() {
        return fuel_upgrade_cost;
    }
}
