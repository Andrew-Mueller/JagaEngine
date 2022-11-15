package com.JagaEngine;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Handler;
import android.os.Message;

import static android.opengl.GLES10.*;
import static android.opengl.GLES20.glViewport;
import static com.JagaEngine.util.Constants.SURFACE_CREATED;

import com.JagaEngine.animation.*;
import com.JagaEngine.collision.CollisionList;
import com.JagaEngine.fragments.*;

public class JagaRenderer implements Renderer
{
    private final Context context;

    private final Handler messageHandler;

    private JagaCamera camera;

    // create an instance of each of the shader types
    private TextureShaderProgram textureProgram;
    private ColorShaderProgram colorProgram;

    private RenderList renderlist;

    private AsyncAnimationList animationList;

    private CollisionList collisionList;

    public JagaCamera getCamera() { return camera; }

    public TextureShaderProgram getTextureProgram() { return textureProgram; }
    public ColorShaderProgram getColorProgram() { return colorProgram; }

    public RenderList getRenderList() { return renderlist; }

    public AnimationList getAnimationList() { return animationList; }

    public CollisionList getCollisionList() { return collisionList; }


    public JagaRenderer()
    {
        this(null, null);
    }

    public JagaRenderer(Context context, Handler handler)
    {
        this.context = context;

        this.messageHandler = handler;

        this.camera = new JagaCamera();

        this.renderlist = new RenderList();

        this.animationList = new AsyncAnimationList();

        this.collisionList = new CollisionList();
    }

    @Override
    public void onSurfaceCreated(GL10 glUnused, EGLConfig config)
    {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // TODO: shaders need to be abstracted out of the library layer and
        //       pushed up into the application layer that owns the objects.
        this.textureProgram = new TextureShaderProgram(context);
        this.colorProgram = new ColorShaderProgram(context);

        // TODO: texture was loaded here in the example application

        // done-ski, send the message letting the UI thread know that the OpenGL surface
        // is done being created
        Message doneMsg = messageHandler.obtainMessage(SURFACE_CREATED, this.colorProgram);
        doneMsg.sendToTarget();
    }

    @Override
    public void onSurfaceChanged(GL10 glUnused, int width, int height)
    {
        glViewport(0, 0, width, height);

        camera.setup(width, height);

        // TODO: something isn't right when the application looses focus.
        //       do the shaders need to be re-loaded?
    }

    @Override
    public void onDrawFrame(GL10 glUnused)
    {
        glClear(GL_COLOR_BUFFER_BIT);

        // TODO: update all of the shader values

        // animate all of the items in the animation list
        try
        {
            this.animationList.animate();
        }
        catch(Exception ex)
        {
            android.util.Log.e("JagaRenderer", "Error animating the items in the scene.", ex);
        }

        // Draw all of the items in the render list
        this.renderlist.Render(camera);
    }
}
