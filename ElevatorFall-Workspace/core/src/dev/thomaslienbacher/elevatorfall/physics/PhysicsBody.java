package dev.thomaslienbacher.elevatorfall.physics;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import dev.thomaslienbacher.elevatorfall.assets.Data;
import dev.thomaslienbacher.elevatorfall.utils.Utils;

/**
 * @author Thomas Lienbacher
 */
public class PhysicsBody {
    private Body body;
    private boolean canRotate = false;
    private String userdata;

    public PhysicsBody(){
        this(false, "");
    }

    public PhysicsBody(boolean canRotate, String userdata) {
        this.canRotate = canRotate;
        this.userdata = userdata;
    }

    private void init(PhysicsSpace space, BodyDef.BodyType type, Vector2 position){
        if(body != null) return;

        BodyDef def = new BodyDef();
        def.type = type;
        def.fixedRotation = !canRotate;

        body = space.get().createBody(def);
        body.setTransform(position, 0);
    }

    public void initAsBox(PhysicsSpace space, BodyDef.BodyType type, Vector2 position, float friction, float width, float height){
        position.add(width / 2, height / 2);
        position.scl(Data.PXL_2_MTR);
        width *= Data.PXL_2_MTR;
        height *= Data.PXL_2_MTR;

        init(space, type, position);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2, height / 2);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = type == BodyDef.BodyType.DynamicBody ? 1.0f : 0;
        def.friction = friction;

        Fixture a = body.createFixture(def);
        a.setUserData(userdata);
        shape.dispose();
    }

    public void initAsCircle(PhysicsSpace space, BodyDef.BodyType type, Vector2 position, float friction, float radius){
        position.scl(Data.PXL_2_MTR);
        radius *= Data.PXL_2_MTR;

        init(space, type, position);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = type == BodyDef.BodyType.DynamicBody ? 1.0f : 0;
        def.friction = friction;

        Fixture a = body.createFixture(def);
        a.setUserData(userdata);
        shape.dispose();
    }

    public void initAsPolygon(PhysicsSpace space, BodyDef.BodyType type, Vector2 position, float friction, Vector2[] vertices){
        position.scl(Data.PXL_2_MTR);
        for(Vector2 v : vertices) v.scl(Data.PXL_2_MTR);

        init(space, type, position);

        PolygonShape shape = new PolygonShape();
        shape.set(vertices);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = type == BodyDef.BodyType.DynamicBody ? 1.0f : 0;
        def.friction = friction;

        Fixture a = body.createFixture(def);
        a.setUserData(userdata);
        shape.dispose();
    }

    public void initAsEdge(PhysicsSpace space, BodyDef.BodyType type, Vector2 position, float friction, Vector2 start, Vector2 end){
        position.scl(Data.PXL_2_MTR);
        start.scl(Data.PXL_2_MTR);
        end.scl(Data.PXL_2_MTR);

        init(space, type, position);

        EdgeShape shape = new EdgeShape();
        shape.set(start, end);

        FixtureDef def = new FixtureDef();
        def.shape = shape;
        def.density = type == BodyDef.BodyType.DynamicBody ? 1.0f : 0;
        def.friction = friction;

        Fixture a = body.createFixture(def);
        a.setUserData(userdata);
        shape.dispose();
    }

    public void addSensor(Vector2 start, Vector2 end){
        start.scl(Data.PXL_2_MTR);
        end.scl(Data.PXL_2_MTR);
        EdgeShape shape = new EdgeShape();
        shape.set(start, end);

        FixtureDef def = new FixtureDef();
        def.isSensor = true;
        def.shape = shape;

        Fixture a = body.createFixture(def);
        a.setUserData(userdata);
    }

    /**
     * Applies impulse to the center of mass of the body
     *
     * @param impulse
     */
    public void applyImpulse(Vector2 impulse){
        impulse.scl(Data.PXL_2_MTR);
        body.applyLinearImpulse(impulse, Vector2.Zero, true);
    }

    public void applyImpulse(float xi, float yi){
        Vector2 impulse = new Vector2(xi, yi);
        impulse.scl(Data.PXL_2_MTR);
        body.applyLinearImpulse(impulse, Vector2.Zero, true);
    }

    public void applyImpulse(Vector2 impulse, Vector2 relative){
        impulse.scl(Data.PXL_2_MTR);
        relative.scl(Data.PXL_2_MTR);
        body.applyLinearImpulse(impulse, relative, true);
    }

    /**
     * Applies force to the center of mass of the body
     *
     * @param force
     */
    public void applyForce(Vector2 force){
        force.scl(Data.PXL_2_MTR);
        body.applyForceToCenter(force, true);
    }

    public void applyForce(float xf, float yf){
        Vector2 force = new Vector2(xf, yf);
        force.scl(Data.PXL_2_MTR);
        body.applyForceToCenter(force, true);
    }

    public void applyForce(Vector2 force, Vector2 relative){
        force.scl(Data.PXL_2_MTR);
        relative.scl(Data.PXL_2_MTR);
        body.applyForce(force, relative, true);
    }

    public void setPositionPxl(Vector2 v){
        v.scl(Data.PXL_2_MTR);
        body.setTransform(v, getAngle() * Utils.DEG_2_RAD);
    }

    public void setAngle(float degrees){
        body.setTransform(body.getPosition(), degrees * Utils.DEG_2_RAD);
    }

    public void setLinearVelocity(Vector2 vel){
        vel.scl(Data.PXL_2_MTR);
        body.setLinearVelocity(vel);
    }

    public void setAngularVelocity(float vel){
        vel *= Data.PXL_2_MTR;
        body.setAngularVelocity(vel);
    }

    public void setRestitution(float restitution){
        body.getFixtureList().get(0).setRestitution(restitution);
    }

    public Body get() {
        return body;
    }

    public Vector2 getPositionPxl(){
        return body.getPosition().cpy().scl(Data.MTR_2_PXL);
    }

    /**
     * @return angle in degrees
     */
    public float getAngle() {
        return body.getAngle() * Utils.RAD_2_DEG;
    }

    public boolean canRotate() {
        return canRotate;
    }
}
