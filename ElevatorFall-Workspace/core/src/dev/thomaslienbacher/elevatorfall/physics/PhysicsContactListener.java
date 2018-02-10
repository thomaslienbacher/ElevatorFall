package dev.thomaslienbacher.elevatorfall.physics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import dev.thomaslienbacher.elevatorfall.Game;
import dev.thomaslienbacher.elevatorfall.actors.Ball;
import dev.thomaslienbacher.elevatorfall.actors.CollideBox;

/**
 * @author Thomas Lienbacher
 */
public class PhysicsContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        if(containsUserdata(contact, CollideBox.USERDATA, Ball.USERDATA)) {
            Gdx.app.log("contact", "ball collided");
            //game ended
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private boolean containsUserdata(Contact contact, Object userdata) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        return a.getUserData().equals(userdata) || b.getUserData().equals(userdata);
    }

    private boolean containsUserdata(Contact contact, Object userdataA, Object userdataB) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();
        return ((a.getUserData().equals(userdataA)) && (b.getUserData().equals(userdataB)))
                || ((a.getUserData().equals(userdataB)) && (b.getUserData().equals(userdataA)));
    }

    private Fixture getFixture(Contact contact, Object userdata) {
        if(contact.getFixtureA().getUserData() == userdata) return contact.getFixtureA();
        if(contact.getFixtureB().getUserData() == userdata) return contact.getFixtureB();
        return null;
    }
}
