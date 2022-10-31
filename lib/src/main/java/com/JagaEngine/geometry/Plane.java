package com.JagaEngine.geometry;


public class Plane
{
    public final Point point;
    public final Vector normal;

    public Plane(Point point, Vector normal)
    {
        this.point = point;
        this.normal = normal;
    }

    public Point intersectionPoint(Ray ray)
    {
        Vector rayToPlaneVector = Vector.vectorBetween(ray.point, this.point);

        float scaleFactor = rayToPlaneVector.dotProduct(this.normal) / ray.vector.dotProduct(this.normal);

        ray.vector.scale(scaleFactor);
        ray.point.translate(ray.getPoint());

        return ray.getPoint();
    }
}
