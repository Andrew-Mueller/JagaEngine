package com.JagaEngine.geometry;

import static android.opengl.Matrix.multiplyMV;

public class Ray
{
    protected final Point point;
    protected final Vector vector;


    public Point getPoint()
    {
        return point;
    }


    public Ray(Point point, Vector vector)
    {
        this.point = point;
        this.vector = vector;
    }

    public float distanceBetween(Point point)
    {
        Vector p1ToPoint = Vector.vectorBetween(this.point, point);

        Point endPoint = this.point;
        endPoint.translate(this.vector.toPoint());
        Vector p2ToPoint = Vector.vectorBetween(endPoint, point);

        // The length of the cross product gives the area of an imaginary parallelogram
        // having the two vectors as sides.  A parallelogram can be thought of as consisting of two
        // triangles, so this is the same as twice the area of the triangle defined by the two vectors.
        float areaOfTriangleTimesTwo = p1ToPoint.crossProduct(p2ToPoint).length();
        float lengthOfBase = this.vector.length();

        // The area of a triangle is also equal to (base * height) / 2.  In
        // other words, the height is equal to (area * 2) / base. The height
        // of this triangle is the distance from the point to the ray.
        float distanceFromPointToRay = areaOfTriangleTimesTwo / lengthOfBase;

        android.util.Log.i("Ray", "Distance to ray " + distanceFromPointToRay);

        return distanceFromPointToRay;
    }

    public boolean intersects(Sphere sphere)
    {
        return distanceBetween(sphere.center) < sphere.radius;
    }

    @Override
    public String toString()
    {
        return new String("Ray (" + point.getX() + ", " + point.getY() + ") Vector (" + vector.getX() + ", " + vector.getY() + ", " + vector.getZ() + ")");
    }
}
