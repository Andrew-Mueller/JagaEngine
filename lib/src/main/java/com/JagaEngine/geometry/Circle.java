package com.JagaEngine.geometry;

public class Circle
{
    protected Point center;
    protected float radius;

    public Point getCenter()
    {
        return center;
    }

    public float getRadius()
    {
        return radius;
    }

    public Circle(Point center, float radius)
    {
        this.center = center;
        this.radius = radius;
    }

    public void scale(float scale)
    {
        radius *= scale;
    }
}
