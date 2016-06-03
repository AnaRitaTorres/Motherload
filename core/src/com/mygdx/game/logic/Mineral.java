package com.mygdx.game.logic;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;

/**
 * Created by Rita on 14/05/2016.
 */
public abstract class Mineral {

    private int weight;
    private int value;
    private int points;
    private MineralType mineral_type;

    protected World world;
    protected Rectangle bounds;
    protected Body body;
    protected MapObject object;
    protected Fixture fixture;
    protected Map map;



    public Mineral(Map map, MapObject object, int weight, int value, int points, MineralType mineral_type) {
        this.weight = weight;
        this.value = value;
        this.points = points;
        this.mineral_type = mineral_type;

        this.object = object;
        this.world = map.getWorld();
        this.bounds = ((RectangleMapObject) object).getRectangle();
        this.map = map;


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Motherload.PPM, (bounds.getY() + bounds.getHeight() / 2) / Motherload.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / Motherload.PPM, bounds.getHeight() / 2 / Motherload.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void drill();
    
    public void setCatgoryFilter(short filterBit)
    {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getCell()
    {
        TiledMapTileLayer layer = (TiledMapTileLayer) map.getMap().getLayers().get(1);
        TiledMapTileLayer.Cell cell = layer.getCell((int) (body.getPosition().x * Motherload.PPM / 16),
                (int) (body.getPosition().y * Motherload.PPM / 16));
        return cell;

    }


}
