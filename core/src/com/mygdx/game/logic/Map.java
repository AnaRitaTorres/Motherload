package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;
import com.mygdx.game.logic.minerals.Bronzium;
import com.mygdx.game.logic.minerals.Diamond;
import com.mygdx.game.logic.minerals.Dirt;
import com.mygdx.game.logic.minerals.Emerald;
import com.mygdx.game.logic.minerals.Goldium;
import com.mygdx.game.logic.minerals.Ironium;
import com.mygdx.game.logic.minerals.Platinium;
import com.mygdx.game.logic.minerals.Ruby;
import com.mygdx.game.logic.minerals.Silverium;


/**
 * The Map Class
 */
public class Map
{
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;

    /**
     * Instantiates a new Map
     * @param play_state current PlayState
     */
    public Map(PlayState play_state)
    {
        maploader = new TmxMapLoader();
        map = maploader.load("map_full.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Motherload.PPM);

        this.world = play_state.getWorld();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            new Dirt(play_state, this,  object);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            new Ironium(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)){
            new Bronzium(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            new Silverium(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            new Platinium(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            new Emerald(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            new Diamond(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            new Ruby(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(11).getObjects().getByType(RectangleMapObject.class)){
            new Goldium(play_state, this,  object);

        }

        for(MapObject object : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2)/ Motherload.PPM, (rect.getY() + rect.getHeight() /2)/ Motherload.PPM);


            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2/ Motherload.PPM, rect.getHeight() / 2/ Motherload.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

    }

    /**
     * returns the orthogonal tiled map render
     * @return the tiled map renderer
     */
    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    /**
     * returns the map world
     * @return map world
     */
    public World getWorld() {
        return world;
    }

    /**
     * returns the map tileMap
     * @return the map tileMap
     */
    public TiledMap getMap() {
        return map;
    }
}
