package com.JagaEngine;

import com.JagaEngine.geometry.Point;
import com.JagaEngine.geometry.Ray;
import com.JagaEngine.geometry.Vector;
import com.JagaEngine.util.MatrixHelper;

import java.util.Arrays;

import static android.opengl.Matrix.invertM;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.multiplyMV;
import static android.opengl.Matrix.translateM;

public class JagaCamera
{
    protected int width;
    protected int height;

    protected final float[] viewMatrix = new float[16];
    protected final float[] projectionMatrix = new float[16];
    protected final float[] viewProjectionMatrix = new float[16];

    protected Vector eyePosition;
    protected Vector lookAtPosition;


    public float[] getViewProjectionMatrix()
    {
        return viewProjectionMatrix;
    }

    public JagaCamera()
    {
        width = 1;
        height = 1;

        Arrays.fill(viewMatrix, 0);
        Arrays.fill(projectionMatrix, 0);
        Arrays.fill(viewProjectionMatrix, 0);

        eyePosition = new Vector(0.0f, -1.2f, 2.2f);
        lookAtPosition = new Vector(0.0f, 0.0f, 0.0f);
    }

    public void setup(int width, int height)
    {
        this.width = width;
        this.height = height;

        android.util.Log.i("JagaCamera", "Setting up Width = " + this.width + " Height = " + this.height);

        MatrixHelper.perspectiveM(projectionMatrix, 45, (float) width / (float) height, 1.0f, 10.0f);

        // Parameters that are passed to this method.
        // setting the eye at (0.0, 1.2, 2.2)
        // center of view at  (0, 0, 0)
        // up vector (0, 1, 0)
        android.opengl.Matrix.setLookAtM(viewMatrix, 0,
                                         eyePosition.getX(), eyePosition.getY(), eyePosition.getZ(),
                                         lookAtPosition.getX(), lookAtPosition.getY(), lookAtPosition.getZ(),
                                         0f, 1f, 0f);

        multiplyMM(viewProjectionMatrix, 0, projectionMatrix, 0, viewMatrix, 0);
    }

    public void MoveEyeTo(Vector position)
    {
        eyePosition.setX(position.getX());
        eyePosition.setY(position.getY());
        eyePosition.setZ(position.getZ());

        setup(width, height);
    }

    public void MoveEyeBy(Vector deltaPosition)
    {
        lookAtPosition.setX(deltaPosition.getX());
        lookAtPosition.setY(deltaPosition.getY());
        lookAtPosition.setZ(deltaPosition.getZ());

        setup(width, height);
    }

    public Ray convertNormalized2DPointToRay(float normalizedX, float normalizedY)
    {
        float[] invertedViewProjectionMatrix = new float[16];

        invertM(invertedViewProjectionMatrix, 0, viewProjectionMatrix, 0);

        // convert these normalized device coordinates into world-space
        // coordinates.  We'll pick a point on the near and far planes,
        // and draw a line between them.  To do this transform, we need
        // to first multiply by the inverse matrix, and then we need
        // to undo the perspective divide.
        final float[] nearPointNdc = {normalizedX, normalizedY, -1, 1};
        final float[] farPointNdc = {normalizedX, normalizedY, 1, 1};

        final float[] nearPointWorld = new float[4];
        final float[] farPointWorld = new float[4];

        multiplyMV(nearPointWorld, 0, invertedViewProjectionMatrix, 0, nearPointNdc, 0);
        multiplyMV(farPointWorld, 0, invertedViewProjectionMatrix, 0, farPointNdc, 0);

        nearPointWorld[0] /= nearPointWorld[3];
        nearPointWorld[1] /= nearPointWorld[3];
        nearPointWorld[2] /= nearPointWorld[3];


        farPointWorld[0] /= farPointWorld[3];
        farPointWorld[1] /= farPointWorld[3];
        farPointWorld[2] /= farPointWorld[3];

        Point nearPointRay = new Point(nearPointWorld[0], nearPointWorld[1], nearPointWorld[2]);
        Point farPointRay = new Point(farPointWorld[0], farPointWorld[1], farPointWorld[2]);

        return new Ray(nearPointRay, Vector.vectorBetween(nearPointRay, farPointRay));
    }

    public Point convertScreenToWorld(float x, float y)
    {
        // SCREEN height & width (ej: 320 x 480)
        float screenW = this.width;
        float screenH = this.height;

        // Auxiliary matrix and vectors
        // to deal with ogl.
        float[] invertedMatrix, transformMatrix, normalizedInPoint, outPoint;

        invertedMatrix = new float[16];
        transformMatrix = new float[16];
        normalizedInPoint = new float[4];
        outPoint = new float[4];

        // Invert y coordinate, as android uses
        // top-left, and ogl bottom-left.
        int oglTouchY = (int) (screenH - y);

       // Transform the screen point to clip
       // space in ogl (-1,1)

        normalizedInPoint[0] = (float) ((x) * 2.0f / screenW - 1.0);
        normalizedInPoint[1] = (float) ((oglTouchY) * 2.0f / screenH - 1.0);
        normalizedInPoint[2] = - 1.0f;
        normalizedInPoint[3] = 1.0f;

       // Obtain the transform matrix and
       //then the inverse.
       // Print("Proj", getCurrentProjection(gl));
       // Print("Model", getCurrentModelView(gl));

        multiplyMM(transformMatrix, 0,
                   projectionMatrix, 0,
                   viewMatrix, 0);


        invertM(invertedMatrix, 0, transformMatrix, 0);

       /* Apply the inverse to the point
       in clip space */
        multiplyMV(outPoint, 0, invertedMatrix, 0, normalizedInPoint, 0);

        if (outPoint[3] == 0.0)
        {
            // Avoid /0 error.
            //Log.e("World coords", "ERROR!");
            return new Point(0.0f, 0.0f, 1.0f);
        }

        // Divide by the 3rd component to find
        // out the real position.
        return new Point((outPoint[0] / outPoint[3]), (outPoint[1] / outPoint[3]), 1.0f);
    }

}
