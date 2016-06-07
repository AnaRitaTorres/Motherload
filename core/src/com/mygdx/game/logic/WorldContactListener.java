package com.mygdx.game.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.Motherload;
import com.mygdx.game.gui.UpgradeScreen;


/**
 * The WorldContactListener
 * detects object collision of driller with minerals and stores
 */
public class WorldContactListener implements ContactListener {

    PlayState play_state;
    Motherload game;

    /**
     * Instantiates a new WorldContactListener
     * @param play_state current PlayState
     * @param game Motherload game
     */
    public WorldContactListener(PlayState play_state, Motherload game)
    {
        this.play_state = play_state;
        this.game = game;
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

        if(fixA.getUserData() == "driller" || fixB.getUserData() == "driller")
        {
            Fixture sensor = fixA.getUserData() == "driller" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() == "gas_station")
            {
                play_state.getGas_station().shop(play_state.getDriller());
            }
        }

        if(fixA.getUserData() == "driller" || fixB.getUserData() == "driller")
        {
            Fixture sensor = fixA.getUserData() == "driller" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() == "trade_center")
            {
                play_state.getTrade_center().shop(play_state.getDriller());
            }
        }

        if(fixA.getUserData() == "driller" || fixB.getUserData() == "driller")
        {
            Fixture sensor = fixA.getUserData() == "driller" ? fixA : fixB;
            Fixture object = sensor == fixA ? fixB : fixA;

            if(object.getUserData() == "upgrade_store")
            {
                game.setScreen(new UpgradeScreen(game, play_state, play_state.getUpgrade_store()));
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
