package com.JagaEngine.android;

import android.opengl.GLSurfaceView;
import android.view.*;

public class JagaTouch implements View.OnTouchListener
{
    private GLSurfaceView glSurfaceView;

    private ITouchHandler touchHandler;

    public JagaTouch(GLSurfaceView glSurfaceView)
    {
        this.glSurfaceView = glSurfaceView;
    }

    public void setTouchHandler(ITouchHandler handler)
    {
        this.touchHandler = handler;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        if (event != null)
        {
            // Convert touch coordinates into normalized device coordinates,
            // keeping in mind that Android's Y coordinates are inverted.
            final float normalizedX = (event.getX() / (float) v.getWidth()) * 2 - 1;
            final float normalizedY = -((event.getY() / (float) v.getHeight()) * 2 - 1);

            if (event.getAction() == MotionEvent.ACTION_DOWN)
            {
                glSurfaceView.queueEvent(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (null != touchHandler)
                        {
                            touchHandler.handleTouchPress(normalizedX, normalizedY);
                        }
                    }
                });
            }
            else if (event.getAction() == MotionEvent.ACTION_MOVE)
            {
                glSurfaceView.queueEvent(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (null != touchHandler)
                        {
                            touchHandler.handleTouchDrag(normalizedX, normalizedY);
                        }
                    }
                });
            }

            return true;
        }
        else
        {
            return false;
        }
    }
}
