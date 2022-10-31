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

    @Override
    public boolean boundCheck(float x, float y)
    {
        boolean status = false;

        if (x >= this.x && x <= this.x + this.width)
        {
            if (y >= this.y && y <= this.y + this.height)
            {
                status = true;
            }
        }

        return status;
    }

    public boolean boundCheck(Ray ray)
    {
        return false;
    }
}
