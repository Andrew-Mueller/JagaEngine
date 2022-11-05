package com.JagaEngine.android.wallpaper;

import android.view.*;
import android.service.wallpaper.WallpaperService.*;

public class JagaWallpaperService extends android.service.wallpaper.WallpaperService
{

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
    }
}