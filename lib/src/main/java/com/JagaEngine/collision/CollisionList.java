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
                    response.doResponse(currentCollision);
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
