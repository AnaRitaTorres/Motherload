package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Motherload;
import com.mygdx.game.gui.InputHandler;

/**
 * Created by Rita on 14/05/2016.
 */
public class Driller extends Sprite implements InputHandler
{
    public World world;
    public Body b2body;
    private TextureRegion simple_sprite;
    private Texture driller_tex;
    private int speed;

    public Driller(World world, int x, int y)
    {
        this.world = world;
        defineDriller(x, y);
        this.speed = 1;
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
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 30f;
        b2body.createFixture(fixtureDef);
        b2body.setLinearDamping(1.5f);


        driller_tex = new Texture("motherload_sprites/ground_right.png");
        simple_sprite = new TextureRegion(driller_tex, 90, 60);
        setBounds(0, 0, 24/Motherload.PPM, 16/Motherload.PPM);
        setRegion(simple_sprite);

        EdgeShape bottom = new EdgeShape();
        bottom.set(new Vector2(-3 / Motherload.PPM, -5 / Motherload.PPM), new Vector2(3 / Motherload.PPM, -5 / Motherload.PPM));
        fixtureDef.shape = bottom;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData("bottom");

        EdgeShape left = new EdgeShape();
        left.set(new Vector2(-5 / Motherload.PPM, -3 / Motherload.PPM), new Vector2(-5 / Motherload.PPM, 3 / Motherload.PPM));
        fixtureDef.shape = left;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData("left");

        EdgeShape right = new EdgeShape();
        right.set(new Vector2(5 / Motherload.PPM, -3 / Motherload.PPM), new Vector2(5 / Motherload.PPM, 3 / Motherload.PPM));
        fixtureDef.shape = right;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData("right");


    }

    public Vector2 inputHandler()
    {
        Vector2 v;

        if (Gdx.input.isTouched())
        {
            int x,y;

            x = Gdx.input.getX();
            y = Gdx.input.getY();

            v = new Vector2(x,y);
        }
        else
            v = new Vector2(0,0);

        return v;
    }

    public void updateTexture() {
        //setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void updateMove(float delta_time)
    {
        Vector2 input = inputHandler();

        if (input.x == 0 && input.y ==0)
            return;

        input.x = input.x - Gdx.graphics.getWidth()/2;
        input.y = input.y - Gdx.graphics.getHeight()/2;
        float angle = (float)Math.atan2(0, 1) - (float)Math.atan2(input.y, input.x);

        Vector2 impulse_x = new Vector2(speed*(float)Math.cos(angle), 0);
        Vector2 impulse_y = new Vector2(0, speed*(float)Math.sin(angle));

        Vector2 linear_velocity = b2body.getLinearVelocity();

        if(linear_velocity.x < 5 && linear_velocity.x > -5)
            b2body.applyLinearImpulse(impulse_x,b2body.getWorldCenter(), true);

        if(linear_velocity.y < 5 && linear_velocity.y > -10)
            b2body.applyLinearImpulse(impulse_y,b2body.getWorldCenter(), true);

    }
}
