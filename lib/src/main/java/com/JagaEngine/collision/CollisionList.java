package com.JagaEngine.collision;

import java.util.Hashtable;
import java.util.Enumeration;

import com.JagaEngine.geometry.Ray;

/**
 * container for collidable objects.
 */
public class CollisionList
{
    protected Hashtable<ICollidable, ICollisionResponse> collisionList;

    public CollisionList()
    {
        collisionList = new Hashtable<ICollidable, ICollisionResponse>();
    }

    public void add(ICollidable collision, ICollisionResponse response)
    {
        collisionList.put(collision, response);
    }

    /**
     * Check the specified x and y positions
     * @param x X location of the point to check.
     * @param y Y location of the point to check.
     * @return true if the specified point collides with any object in the list.  false if no
     *         collision occurred.  The associated response is called for all collisions that occur.
     */
    public boolean boundCheck(float x, float y)
    {
        boolean collision = false;

        Enumeration<ICollidable> keys = collisionList.keys();

        while (keys.hasMoreElements())
        {
            ICollidable currentCollision = keys.nextElement();

            // Check if the collision occurred and if it has, perform the response.
            if (currentCollision.boundCheck(x, y))
            {
                collision = true;

                android.util.Log.i("CollisionList", "COLLISION FOUND AT (" + x + ", " + y + ")");

                ICollisionResponse response = collisionList.get(currentCollision);

                if (null != response)
                {
                    android.util.Log.i("CollisionList", "Trying the response...");
                    response.doResponse();
                }
                else
                {
                    android.util.Log.i("CollisionList", "NO Response Found!");
                }
            }
        }

        return collision;
    }

    public boolean boundCheck(Ray ray)
    {
        boolean collision = false;

        Enumeration<ICollidable> keys = collisionList.keys();

        while (keys.hasMoreElements())
        {
            ICollidable currentCollision = keys.nextElement();

            // Check if the collision occurred and if it has, perform the response.
            if (currentCollision.boundCheck(ray))
            {
                collision = true;

                android.util.Log.i("CollisionList", "COLLISION FOUND AT (" + ray.toString() + ")");

                ICollisionResponse response = collisionList.get(currentCollision);

                if (null != response)
                {
                    android.util.Log.i("CollisionList", "Trying the response...");
                    response.doResponse();
                }
                else
                {
                    android.util.Log.i("CollisionList", "NO Response Found!");
                }
            }
        }

        return collision;
    }
}
