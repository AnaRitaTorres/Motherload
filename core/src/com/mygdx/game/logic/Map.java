package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;

/**
 * Created by Rita on 17/05/2016.
 */

public class Map
{
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private World world;
    private Box2DDebugRenderer b2dr;

    public Map(World world, Box2DDebugRenderer b2dr)
    {
        maploader = new TmxMapLoader();
        map = maploader.load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1/ Motherload.PPM);

        this.world = world;
        this.b2dr = b2dr;

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2) / Motherload.PPM, (rect.getY() + rect.getHeight() /2) / Motherload.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2/ Motherload.PPM, rect.getHeight() / 2/ Motherload.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        for(MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect =((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX() + rect.getWidth() / 2)/ Motherload.PPM, (rect.getY() + rect.getHeight() /2)/ Motherload.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth() / 2/ Motherload.PPM, rect.getHeight() / 2/ Motherload.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

    }

    public OrthogonalTiledMapRenderer getRenderer() {
        return renderer;
    }

    public Box2DDebugRenderer getB2dr() {
        return b2dr;
    }
}
