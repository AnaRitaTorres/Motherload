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
    private Box2DDebugRenderer b2dr;

    public PlayState()
    {
        world = new World(new Vector2(0, -10), true);
        b2dr = new Box2DDebugRenderer();

        map = new Map(world, b2dr);
    }

    public World getWorld() {
        return world;
    }

    public Map getMap() {
        return map;
    }
}
