package com.JagaEngine.util;

public class MatrixHelper
{
    public static void perspectiveM(float[] m, float yFovInDegrees, float aspect, float n, float f)
    {
        final float angleInRadians = (float)(yFovInDegrees * Math.PI / 180.0f);
        
        final float a = (float) (1.0f / Math.tan(angleInRadians / 2.0f));
        
        // OpenGL stores matrix data in column-major order.  data is written one
        // column at a time instead of per row.
        m[0] = a / aspect;
        m[1] = 0.0f;
        m[2] = 0.0f;
        m[3] = 0.0f;
        
        m[4] = 0.0f;
        m[5] = a;
        m[6] = 0.0f;
        m[7] = 0.0f;
        
        m[8]  = 0.0f;
        m[9]  = 0.0f;
        m[10] = -((f + n) / (f - n));
        m[11] = -1.0f;
        
        m[12] = 0.0f;
        m[13] = 0.0f;
        m[14] = -((2.0f * f * n) / (f - n));
        m[15] = 0.0f;
    }
}
