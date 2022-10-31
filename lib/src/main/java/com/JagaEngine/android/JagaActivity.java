package com.JagaEngine.android;

import android.app.Activity;
import android.app.ActivityManager;

import android.content.Context;

import android.opengl.GLSurfaceView;

import android.os.Build;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.JagaEngine.JagaRenderer;

import static com.JagaEngine.util.Constants.SURFACE_CREATED;

public class JagaActivity extends Activity
{
    /**
     * target for openGL rendering target.
     */
    private GLSurfaceView glSurfaceView;

    private boolean rendererSet = false;

    // message handler for passing messages back and forth
    // between the openGL rendering thread and the UI thread
    protected Handler messageHandler;

    protected android.opengl.GLSurfaceView.Renderer renderer;

    // id of the menu resource.
    // NOTE: this is intended to be set by the sub-class.
    protected int menuResID;

    protected JagaTouch touch;

    public android.opengl.GLSurfaceView.Renderer getRenderer()
    {
        return renderer;
    }

    public void setTouchHandler(ITouchHandler handler)
    {
        if (null != touch)
        {
            touch.setTouchHandler(handler);
        }
    }

    public JagaActivity()
    {
        this(null);
    }

    public JagaActivity(android.opengl.GLSurfaceView.Renderer renderer)
    {
        this.renderer = renderer;

        messageHandler = new Handler(Looper.getMainLooper())
        {
            // operation to perform when the handler receives a
            // message to process.
            @Override
            public void handleMessage(Message inputMessage)
            {
                // TODO: modify to pass the renderer context to allow creation of shaders and
                //       textures in the scene creation

                // NOTE: intentionally ignoring the data passed from the
                // input message because we aren't using it.
                //obj = (objcast)inputMessage.obj;

                switch (inputMessage.what)
                {
                    case SURFACE_CREATED:
                    {
                        initializeScene();
                        break;
                    }
                    default:
                    {
                        super.handleMessage(inputMessage);
                    }
                }
            }
        };
    }

    /**
     * The application level primitives are setup and added to the scene in
     * the overridden version of this function.
     *
     * This is done to facilitate the communication between the opengl render
     * thread starting up, message handler above, and the creation of the objects
     * that rely on the renderer.
     */
    protected void initializeScene() {}

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        final ActivityManager activityManager = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
        
        final android.content.pm.ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        
        final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000
                || (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")));
        
        glSurfaceView = new GLSurfaceView(this);

        if (supportsEs2)
        {
            Log.v("JagaActivity:onCreate", "setting the surfaceView's client version");

            // Request an OpenGL ES 2.0 compatible context.
            glSurfaceView.setEGLContextClientVersion(2);

            this.renderer = new JagaRenderer(this, messageHandler);

            // Assign our renderer.
            glSurfaceView.setRenderer(this.renderer);

            rendererSet = true;
        }
        else
        {
            Toast.makeText(this, "This device does not support OpenGL ES 2.0.", Toast.LENGTH_LONG).show();
            return;
        }


        // TODO: this is weird...
        // why do we need to both pass the surfaceView, and set the property?
        touch = new JagaTouch(glSurfaceView);
        glSurfaceView.setOnTouchListener(touch);

        
        setContentView(glSurfaceView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(menuResID, menu);

        return true;
    }
    
    @Override
    protected void onPause()
    {
        super.onPause();
        
        if (rendererSet)
        {
            glSurfaceView.onPause();
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
