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

    protected int weight;
    protected int value;
    protected int points;

    protected World world;
    protected Rectangle bounds;
    protected Body body;
    protected MapObject object;
    protected Fixture fixture;
    protected Map map;
    protected PlayState play_state;



    public Mineral(PlayState play_state, Map map, MapObject object, int weight, int value, int points) {
        this.weight = weight;
        this.value = value;
        this.points = points;

        this.play_state = play_state;
        this.map = map;
        this.world = play_state.getWorld();
        this.object = object;
        this.bounds = ((RectangleMapObject) object).getRectangle();


        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();
        fdef.filter.maskBits = Motherload.DRILLER_BIT;

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / Motherload.PPM, (bounds.getY() + bounds.getHeight() / 2) / Motherload.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / Motherload.PPM, bounds.getHeight() / 2 / Motherload.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void drill();
    
    public void setCategoryFilter(short filterBit)
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
