package com.JagaEngine.collision;


import com.JagaEngine.geometry.Ray;

public class BoundingBox implements ICollidable
{
    protected float x;
    protected float y;
    protected float width;
    protected float height;

    public float getX() { return x; }
    public float getY() { return y; }
    public float getWidth() { return width; }
    public float getHeight() { return height; }

    public BoundingBox()
    {
        this(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public BoundingBox(float x, float y, float width, float height)
    {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean boundCheck(Ray ray)
    {
        return false;
    }
}
