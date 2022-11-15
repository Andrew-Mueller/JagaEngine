package com.JagaEngine.geometry.quads;

import com.JagaEngine.JagaCamera;
import com.JagaEngine.collision.ICollisionResponse;
import com.JagaEngine.geometry.Quad;
import com.JagaEngine.collision.ICollidable;
import com.JagaEngine.geometry.Ray;
import com.JagaEngine.geometry.Sphere;
import com.JagaEngine.state.State;
import com.JagaEngine.state.StateMachine;
import com.JagaEngine.state.Transition;
import com.JagaEngine.util.IPublisher;
import com.JagaEngine.util.ISubscriber;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 */
public class CollidableQuad extends Quad implements ICollidable, ICollisionResponse, IPublisher
{
    // TODO: the collision state and
    public enum COLLISION_TRANSITIONS
    {
       COLLISION,
       NONCOLLISION
    }

    protected class CollisionState extends State
    {
        @Override
        public void enter()
        {
            // change the color when the quad is touched.
            color.setX(0.0f);
            color.setY(0.0f);
            color.setZ(1.0f);

            publish(COLLISION_TRANSITIONS.COLLISION.ordinal());
        }

        @Override
        public void exit()
        {
            // change the color when the quad is done being touched.
            color.setX(1.0f);
            color.setY(0.0f);
            color.setZ(0.0f);

            publish(COLLISION_TRANSITIONS.NONCOLLISION.ordinal());
        }
    }

    protected StateMachine stateMachine;
    protected CollisionState collisionState;
    protected State nonCollisionState;
    
    protected ArrayList<ISubscriber> subscriberList;

    // TODO: this doesn't need to be a member when done.
    protected Sphere boundingSphere;

    public CollidableQuad()
    {
        stateMachine = new StateMachine();

        nonCollisionState = new State();
        collisionState = new CollisionState();

        nonCollisionState.addTransition(new Transition(COLLISION_TRANSITIONS.COLLISION.ordinal(), collisionState));
        collisionState.addTransition(new Transition(COLLISION_TRANSITIONS.NONCOLLISION.ordinal(), nonCollisionState));

        stateMachine.add(nonCollisionState);
        stateMachine.add(collisionState);

        subscriberList = new ArrayList<ISubscriber>();
    }

    @Override
    public boolean boundCheck(Ray ray)
    {
        boundingSphere = new Sphere(new com.JagaEngine.geometry.Point(this.getX(), this.getY(), this.getZ()), this.getHeight() / 2f);

        return ray.intersects(boundingSphere);
    }

    @Override
    public void doResponse(Object collisionObject)
    {
        android.util.Log.i("CollidableQuad", "Doing the M F'n response!");

        // TODO: send a signal that a collision happened?

        //stateMachine.signal(0);
        super.setColor(0.0f, 0.0f, 1.0f);
    }

    /**
     * implements IPublisher
     * publish the collision and non-collision signals
     */
    @Override
    public void publish(int signal)
    {
        Iterator<ISubscriber> subIter = subscriberList.iterator();
        while (subIter.hasNext())
        {
            ISubscriber sub = (ISubscriber)subIter;
            sub.handle(signal);

            subIter.next();
        }
    }

    /**
     * implements IPublisher
     * add the specified subscriber to this publisher.
     * @param subscriber The object that is subscribing to this publisher.
     * @param signals List of signals to subscribe to from the publisher
     */
    @Override
    public void addSubscriber(ISubscriber subscriber, List<Integer> signals)
    {
        subscriberList.add(subscriber);
    }

    @Override
    public void Render(JagaCamera camera)
    {
        super.Render(camera);

        if (null != boundingSphere)
        {
            boundingSphere.Render(camera);
        }
    }
}
