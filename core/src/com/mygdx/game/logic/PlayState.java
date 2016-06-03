package com.mygdx.game.logic;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Rita on 14/05/2016.
 */
public class PlayState {

    private Map map;
    private World world;


    public Mineral bottom_contact = null;
    public Mineral left_contact = null;
    public Mineral right_contact = null;

    public int score = 0;

    public PlayState()
    {
        world = new World(new Vector2(0, -10), true);
        map = new Map(world);
        world.setContactListener(new WorldContactListener(this));

    }

    public World getWorld() {
        return world;
    }

    public Map getMap() {
        return map;
    }
}
