package com.JagaEngine.collision;

import com.JagaEngine.geometry.Ray;

/**
 * Interface for checking if an object is colliding with this object.
 */
public interface ICollidable
{
    /**
     * Returns true if the specified ray interesects the object being checked.
     * @param ray Line extending from the specified point and extending in the direction of the vector.
     * @return true if the specified ray collides with the object.  false if it does not.
     */
    public boolean boundCheck(Ray ray);
}
