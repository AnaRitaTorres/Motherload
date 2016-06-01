package com.mygdx.game.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Rita on 17/05/2016.
 */
public abstract class InputHandler
{/*
    public void inputHandler()
    {
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && driller.b2body.getLinearVelocity().y <= 6.5)
        {
            driller.b2body.applyLinearImpulse(new Vector2(0, 3f), driller.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && driller.b2body.getLinearVelocity().x >= -6.5)
        {
            driller.b2body.applyLinearImpulse(new Vector2(-0.8f, 0), driller.b2body.getWorldCenter(), true);
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && driller.b2body.getLinearVelocity().x <= 6.5)
        {
            driller.b2body.applyLinearImpulse(new Vector2(0.8f, 0), driller.b2body.getWorldCenter(), true);
        }
    }

    public Input.Keys keyboardHandler()
    {
        Input.Keys key;
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            System.out.print("UP\n");

        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            System.out.print("DOWN\n");

        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            System.out.print("LEFT\n");

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            System.out.print("RIGHT\n");
    }*/

    int x, y, dx, dy;
    if(Gdx.input.isTouched()) {
    x = Gdx.input.getX();
    y = Gdx.input.getY();


    dx = Gdx.graphics.getWidth()/2 - x;
    dy = Gdx.graphics.getHeight()/2 - y;

}



}

