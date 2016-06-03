package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;


/**
 * Created by Daniel on 02/06/2016.
 */
public class WorldContactListener implements ContactListener {

    PlayState play_state;

    public WorldContactListener(PlayState play_state)
    {
        this.play_state = play_state;
    }


    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "bottom" || fixB.getUserData() == "bottom")
        {
            Fixture sensor = fixA.getUserData() == "bottom" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                play_state.bottom_contact = (Mineral)object.getUserData();
                return;
            }
        }

        if(fixA.getUserData() == "left" || fixB.getUserData() == "left")
        {
            Fixture sensor = fixA.getUserData() == "left" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                play_state.left_contact = (Mineral) object.getUserData();
                return;
            }
        }

        if(fixA.getUserData() == "right" || fixB.getUserData() == "right")
        {
            Fixture sensor = fixA.getUserData() == "right" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                play_state.right_contact = (Mineral)object.getUserData();
                return;
            }
        }

    }

    @Override
    public void endContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if(fixA.getUserData() == "bottom" || fixB.getUserData() == "bottom")
        {
            Fixture sensor = fixA.getUserData() == "bottom" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                if(play_state.bottom_contact == object.getUserData())
                    play_state.bottom_contact = null;
                return;
            }
        }

        if(fixA.getUserData() == "left" || fixB.getUserData() == "left")
        {
            Fixture sensor = fixA.getUserData() == "left" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                if(play_state.left_contact == object.getUserData())
                    play_state.left_contact = null;
                return;
            }
        }

        if(fixA.getUserData() == "right" || fixB.getUserData() == "right")
        {
            Fixture sensor = fixA.getUserData() == "right" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() instanceof Mineral)
            {
                if(play_state.right_contact == object.getUserData())
                    play_state.right_contact = null;
                return;
            }
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
