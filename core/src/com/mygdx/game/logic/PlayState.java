package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;

/**
 * The PlayState class
 */
public class PlayState{

    private Map map;
    private World world;
    private Driller driller;
    private Motherload game;
    private GasStation gas_station;
    private TradeCenter trade_center;
    private UpgradeStore upgrade_store;


    public Mineral bottom_contact = null;
    public Mineral left_contact = null;
    public Mineral right_contact = null;

    public int score;
    private int money;

    /**
     * Instantiates a new PlayState
     * @param game Motherload
     */
    public PlayState(Motherload game)
    {
        this.game = game;

        this.score = 0;
        this.money = 500;

        world = new World(new Vector2(0, -10), true);
        map = new Map(this);
        world.setContactListener(new WorldContactListener(this, game));
        this.driller = new Driller(this, 40, 1200);
        this.gas_station = new GasStation(this, 9, -27);
        this.trade_center = new TradeCenter(this, 26, -27);
        this.upgrade_store = new UpgradeStore(this, 46, -27);

    }

    /**
     * checks if the game is over
     * @return true if game is over, false is game is not over
     */
    public boolean checkGameOver()
    {
        boolean ret = false;

        if(driller.getHealth() == 0)
            ret = true;

        if(driller.getFuel() <= 0)
            ret = true;

        return ret;
    }

    /**
     * return game world
     * @return game world
     */
    public World getWorld() {
        return world;
    }

    /**
     * return game map
     * @return game map
     */
    public Map getMap() {
        return map;
    }

    /**
     * return PlayState driller
     * @return PlayState driller
     */
    public Driller getDriller() {
        return driller;
    }

    /**
     * return game score
     * @return game score
     */
    public int getScore() {
        return score;
    }

    /**
     * return player money
     * @return player money
     */
    public int getMoney() {
        return money;
    }

    /**
     * return game trade center
     * @return game trade center
     */
    public TradeCenter getTrade_center() {
        return trade_center;
    }

    /**
     * return game gas station
     * @return game gas station
     */
    public GasStation getGas_station() {
        return gas_station;
    }

    /**
     * updates player money value
     * @param money players new money value
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * return upgrade store
     * @return game upgrade store
     */
    public UpgradeStore getUpgrade_store() {
        return upgrade_store;
    }

    /**
     * return game
     * @return game
     */
    public Motherload getGame() {
        return game;
    }
}
