package com.mygdx.game.logic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    private TextureRegion simple_sprite;
    private Texture driller_tex;

    public Driller(World world, int x, int y)
    {
        this.world = world;
        defineDriller(x, y);
    }

    public void defineDriller(int x, int y)
    {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x/ Motherload.PPM,y/ Motherload.PPM);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5/ Motherload.PPM);
        fixtureDef.shape = shape;
        b2body.createFixture(fixtureDef);

        driller_tex = new Texture("motherload_sprites/ground_right.png");
        simple_sprite = new TextureRegion(driller_tex, 90, 60);
        setBounds(0, 0, 24/Motherload.PPM, 16/Motherload.PPM);
        setRegion(simple_sprite);
    }


    public void update(float delta_time)
    {
        setPosition(b2body.getPosition().x - getWidth()/ 2, b2body.getPosition().y - getHeight()/2);
    }

    public void moveDriller()
    {

    }


}
