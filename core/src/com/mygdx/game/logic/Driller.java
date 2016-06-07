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

import java.util.ArrayList;

/**
 * Created by Rita on 14/05/2016.
 */
public class Driller extends Sprite implements InputHandler
{
    public World world;
    PlayState play_state;
    public Body b2body;
    private TextureRegion simple_sprite;
    private Texture driller_tex;

    private int speed;
    private ArrayList<Mineral> minerals;
    private int capacity;
    private int health;
    private int max_health;
    private float fuel;
    private int max_fuel;


    private float last_vertical_velocity;


    public Driller(PlayState play_state, int x, int y)
    {
        this.play_state = play_state;
        this.world = play_state.getWorld();
        defineDriller(x, y);
        this.speed = 1;
        this.capacity = 5;
        this.health = 90;
        this.minerals = new ArrayList<Mineral>();
        this.last_vertical_velocity = 0;
        this.max_health = 100;
        this.max_fuel = 100;
        this.fuel = 50;
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
        fixtureDef.filter.categoryBits = Motherload.DRILLER_BIT;
        fixtureDef.filter.maskBits = Motherload.DEFAULT_BIT | Motherload.MINERAL_BIT | Motherload.ROCK_BIT;

        fixtureDef.shape = shape;
        fixtureDef.restitution = 0.1f;
        fixtureDef.friction = 30f;
        b2body.setLinearDamping(1.5f);

        b2body.createFixture(fixtureDef).setUserData("driller");


        driller_tex = new Texture("motherload_sprites/ground_right.png");
        simple_sprite = new TextureRegion(driller_tex, 90, 60);
        setBounds(0, 0, 24/Motherload.PPM, 16/Motherload.PPM);
        setRegion(simple_sprite);


        //colision sensors
        EdgeShape bottom = new EdgeShape();
        bottom.set(new Vector2(-0 / Motherload.PPM, -5 / Motherload.PPM), new Vector2(0 / Motherload.PPM, -5 / Motherload.PPM));
        fixtureDef.shape = bottom;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData("bottom");

        EdgeShape left = new EdgeShape();
        left.set(new Vector2(-5 / Motherload.PPM, -0 / Motherload.PPM), new Vector2(-5 / Motherload.PPM, 0 / Motherload.PPM));
        fixtureDef.shape = left;
        fixtureDef.isSensor = true;
        b2body.createFixture(fixtureDef).setUserData("left");

        EdgeShape right = new EdgeShape();
        right.set(new Vector2(5 / Motherload.PPM, -0 / Motherload.PPM), new Vector2(5 / Motherload.PPM, 0 / Motherload.PPM));
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
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }


    public void updateMove(float delta_time)
    {
        Vector2 input = inputHandler();

        if (input.x == 0 && input.y ==0)
            return;


        input.x = input.x - Gdx.graphics.getWidth()/2;
        input.y = input.y - Gdx.graphics.getHeight()/2;

        float angle = (float)Math.atan2(0, 1) - (float)Math.atan2(input.y, input.x);

        if(angle > -100*Math.PI/180 && angle < -80*Math.PI/180)
            if(b2body.getLinearVelocity().x == 0 && b2body.getLinearVelocity().y == 0 )
                if(play_state.bottom_contact != null)
                {
                    Gdx.app.log("bottom", "");
                    play_state.bottom_contact.drill();
                    decreaseFuel();
                }

        if(angle > -10*Math.PI/180 && angle < 10*Math.PI/180)
            if(b2body.getLinearVelocity().x == 0 && b2body.getLinearVelocity().y == 0 )
                if(play_state.right_contact != null && play_state.bottom_contact != null)
                {
                    Gdx.app.log("right", "");
                    play_state.right_contact.drill();
                    decreaseFuel();
                }

        if(angle > 170*Math.PI/180 || angle < -170*Math.PI/180)
            if(b2body.getLinearVelocity().x == 0 && b2body.getLinearVelocity().y == 0 )
                if(play_state.left_contact != null && play_state.bottom_contact != null)
                {
                    Gdx.app.log("left", "");
                    play_state.left_contact.drill();
                    decreaseFuel();
                }


        Vector2 impulse_x = new Vector2(speed*(float)Math.cos(angle), 0);
        Vector2 impulse_y = new Vector2(0, speed*(float)Math.sin(angle));

        Vector2 linear_velocity = b2body.getLinearVelocity();

        if(linear_velocity.x < 5 && linear_velocity.x > -5)
            b2body.applyLinearImpulse(impulse_x,b2body.getWorldCenter(), true);

        if(linear_velocity.y < 5 && linear_velocity.y > -10 && impulse_y.y > 0f)
            b2body.applyLinearImpulse(impulse_y,b2body.getWorldCenter(), true);

    }

    public void addMineral(Mineral mineral)
    {
        if(minerals.size() < capacity)
            minerals.add(mineral);
        System.out.print("minerals count: ");
        System.out.print(minerals.size());
        System.out.print("\n");
    }

    public void updateHealth()
    {
        float current_vertical_velocity = b2body.getLinearVelocity().y;
        float delta_vy = last_vertical_velocity - current_vertical_velocity;


        if(delta_vy < -6) {
            health = health - 30;

            if(health < 0)
                health = 0;

            System.out.print("health:");
            System.out.print(health);
            System.out.print("\n");


        }
        last_vertical_velocity = current_vertical_velocity;

    }

    public void decreaseHealth(int lost_health)
    {
        health = health - lost_health;

        if(health < 0)
            health = 0;
    }

    public void updateFuel(float delta_time)
    {
        setFuel(getFuel() - delta_time);
    }

    public void clearMinerals()
    {
        minerals.clear();
    }

    public void decreaseFuel()
    {
        fuel = fuel - 0.1f;
    }


    public int getMax_fuel() {
        return max_fuel;
    }

    public float getFuel() {
        return fuel;
    }

    public int getMax_health() {
        return max_health;
    }

    public int getHealth() {
        return health;
    }

    public int getCapacity() {
        return capacity;
    }

    public ArrayList<Mineral> getMinerals() {
        return minerals;
    }

    public void setFuel(float fuel) {
        this.fuel = fuel;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void addMax_fuel(int max_fuel) {
        this.max_fuel += max_fuel;
    }

    public void addMax_health(int max_health) {
        this.max_health += max_health;
    }

    public void addCapacity(int capacity) {
        this.capacity += capacity;
    }
}
