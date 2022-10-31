package com.JagaEngine.util;

import static android.opengl.GLES20.*;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class TextureHelper
{
    public static final String TAG = "TextureHelper";
    
    public static int loadTexture(Context context, int resourceId)
    {
        final int[] textureObjectIds = new int[1];
        glGenTextures(1, textureObjectIds, 0);
        
        if (textureObjectIds[0] == 0)
        {
            if (LoggerConfig.ON)
            {
                Log.w(TAG, "Could not generate a new OpenGL texture object.");
            }
            
            return 0;
        }
            
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;
        
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        
        if (null == bitmap)
        {
            if (LoggerConfig.ON)
            {
                Log.w(TAG, "Resource ID " + resourceId + " could not be decoded.");
            }
            
            glDeleteTextures(1, textureObjectIds, 0);
            
            return 0;
        }
        
        // notify openGL that future texture calls should be applied to this texture. 
        glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);
        
        // choose min and max texture filtering for the texture.
        // Minification: bilinear filtering with interpolation between mipmap levels
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);

        // Magnification: binlinear filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        
        // load the bitmap data into openGL
        android.opengl.GLUtils.texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
        
        // allow the android bitmap to be garbage collected
        bitmap.recycle();
        
        // generate the mipmaps for the texture
        glGenerateMipmap(GL_TEXTURE_2D);
        
        // unbind from the texture to prevent future changes.
        glBindTexture(GL_TEXTURE_2D, 0);

        return textureObjectIds[0];
    }
}
