package com.JagaEngine.android.wallpaper;

import android.app.ActivityManager;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.view.*;
import android.service.wallpaper.WallpaperService.*;

public class JagaWallpaperService extends android.service.wallpaper.WallpaperService
{
    private JagaWallpaperEngine.WallpaperGLSurfaceView glSurfaceView;
    private boolean renderSet;

    @Override
    public Engine onCreateEngine()
    {
        return null;
    }

    public class JagaWallpaperEngine extends android.service.wallpaper.WallpaperService.Engine
    {
        @Override
        public void onCreate(SurfaceHolder surfaceHolder)
        {
            super.onCreate(surfaceHolder);

            glSurfaceView = new WallpaperGLSurfaceView(JagaWallpaperService.this);

            // Check if the system supports OpenGL ES 2.0.
            final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);

            final android.content.pm.ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();

            final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000
                    || (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                    && (Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")));
        }

        @Override
        public void onVisibilityChanged(boolean visible)
        {
            super.onVisibilityChanged(visible);
        }

        @Override
        public void onDestroy()
        {
            super.onDestroy();
        }

        /**
         *
         */
        class WallpaperGLSurfaceView extends GLSurfaceView
        {
            WallpaperGLSurfaceView(Context context)
            {
                super(context);
            }

            @Override
            public SurfaceHolder getHolder()
            {
                return getSurfaceHolder();
            }

            public void onWallpaperDestroy()
            {
                super.onDetachedFromWindow();
            }

        }

    }
}