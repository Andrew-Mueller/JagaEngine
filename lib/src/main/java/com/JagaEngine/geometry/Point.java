package com.JagaEngine.geometry;

/**
 * Point is a R3 representation of a point in space.
 */
public class Point
{
    protected float x;
    protected float y;
    protected float z;

    public float getX()
    {
        return x;
    }

    public float getY()
    {
        return y;
    }

    public float getZ()
    {
        return z;
    }

    public Point(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void translateY(float distance)
    {
        this.y += distance;
    }

    public void translate(Point delta)
    {
        this.x += delta.x;
        this.y += delta.y;
        this.z += delta.z;
    }
}
