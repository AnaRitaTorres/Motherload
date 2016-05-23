package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;

/**
 * Created by Rita on 14/05/2016.
 */
public class Driller extends Sprite
{
    public World world;
    public Body b2body;

    public Driller(World world)
    {
        this.world = world;
        defineDriller();
    }

    public void defineDriller()
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(40/ Motherload.PPM,1500/ Motherload.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ Motherload.PPM);
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);
    }

    public void moveDriller()
    {

    }


}
