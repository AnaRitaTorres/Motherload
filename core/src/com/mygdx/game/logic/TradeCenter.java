package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

/**
 * Created by Rita on 14/05/2016.
 */
public class TradeCenter
{
    PlayState play_state;
    World world;

    public TradeCenter(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        defineTradeCenter(x,y);
    }

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
