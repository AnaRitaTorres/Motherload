package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Rita on 14/05/2016.
 */
public class PlayState extends State{

    private Map map;
    private World world;
    private Driller driller;
    private GasStation gas_station;
    private TradeCenter trade_center;


    public Mineral bottom_contact = null;
    public Mineral left_contact = null;
    public Mineral right_contact = null;

    public int score;
    private int money;


    public PlayState()
    {
        this.score = 0;
        this.money = 10;

        world = new World(new Vector2(0, -10), true);
        map = new Map(this);
        world.setContactListener(new WorldContactListener(this));
        this.driller = new Driller(this, 40, 1200);
        this.gas_station = new GasStation(this, 9, -27);
        this.trade_center = new TradeCenter(this, 34, -27);

    }

    public World getWorld() {
        return world;
    }

    public Map getMap() {
        return map;
    }

    public Driller getDriller() {
        return driller;
    }

    public int getScore() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public TradeCenter getTrade_center() {
        return trade_center;
    }

    public GasStation getGas_station() {
        return gas_station;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
