package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * The TradeCenter class
 */
public class TradeCenter
{
    PlayState play_state;
    World world;

    /**
     * Instantiates a new TradeCenter
     * @param play_state current PlayState
     * @param x x position of trade center
     * @param y y position of trade center
     */
    public TradeCenter(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        defineTradeCenter(x,y);
    }

    /**
     * defines all the attributes of the trade center
     * @param x x position of trade center
     * @param y y position of trade center
     */
    private void defineTradeCenter(int x, int y)
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
        body.createFixture(fdef).setUserData("trade_center");


    }

    /**
     * trades the player minerals for money
     * @param driller driller currently in the play State
     */
    public void shop(Driller driller)
    {
        int money = 0;
        ArrayList<Mineral> minerals = driller.getMinerals();

        for(int i = 0; i < minerals.size(); i++)
        {
            money = money + minerals.get(i).value;
        }

        play_state.setMoney(play_state.getMoney() + money);
        driller.clearMinerals();
    }




}
