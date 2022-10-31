package com.JagaEngine.geometry;


public class Vector
{
    protected float x, y, z;

    public float getX() { return x; }
    public void setX(float newX) { this.x = newX; }

    public float getY() { return y; }
    public void setY(float newY) { this.y = newY; }

    public float getZ() { return z; }
    public void setZ(float newZ) { this.z = newZ; }

    public Vector(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector(Vector copy)
    {
        this.x = copy.x;
        this.y = copy.y;
        this.z = copy.z;
    }

    public static Vector vectorBetween(Point from, Point to)
    {
        return new Vector(to.x - from.x, to.y - from.y, to.z - from.z);
    }

    public static Vector difference(Vector left, Vector right)
    {
        return new Vector(left.x - right.x, left.y - right.y, left.z - right.z);
    }

    public static Vector sum(Vector left, Vector right)
    {
        return new Vector(left.x + right.x, left.y + right.y, left.z + right.z);
    }

    public float length()
    {
        return (float)Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public Vector crossProduct(Vector other)
    {
        // v1 * this = |v1|*|this|*sin(theta) * normal.length
        // a x b = |a| * |b| sin(theta) * normal.length

        return new Vector((y * other.z) - (z * other.y),
                (z * other.x) - (x * other.z),
                (x * other.y) - (y * other.x));
    }

    public float dotProduct(Vector other)
    {
        // |a| * |b| cos (theta)
        return (x * other.x) + (y * other.y) + (z * other.z);
    }

    public void scale(float f)
    {
        x *= f;
        y *= f;
        z *= f;
    }

    public void normalize()
    {
        float vectorLength = length();

        this.x = this.x / vectorLength;
        this.y = this.y / vectorLength;
        this.z = this.z / vectorLength;
    }

    public Point toPoint()
    {
        return new Point(x, y, z);
    }

    public boolean equals(Object obj)
    {
        boolean status = false;

        if (obj instanceof Vector)
        {
            Vector v = (Vector)obj;

            if ((this.x == v.x) &&
                (this.y == v.y) &&
                (this.z == v.z))
            {
                status = true;
            }
        }

        return status;
    }
}
