package com.JagaEngine.geometry;

import com.JagaEngine.JagaCamera;
import com.JagaEngine.data.VertexArray;
import com.JagaEngine.fragments.ShaderProgram;

import static android.opengl.Matrix.*;

public abstract class Primitive implements com.JagaEngine.IRenderItem
{
    protected VertexArray vertexArrayData = null;

    protected final float[] world = new float[16];
    protected final float[] position = new float[16];
    protected final float[] rotation = new float[16];
    protected final float[] scale = new float[16];

    // create the identity matrix used for creating the translation, rotation,
    // and scaling matrix.
    protected final float[] identity = new float[16];

    protected Vector yawPitchRoll;

    protected Vector originalScale;

    protected ShaderProgram shader;

    public void setShader(ShaderProgram shader)
    {
        this.shader = shader;
    }

    public Primitive()
    {
        setIdentityM(world, 0);
        setIdentityM(position, 0);
        setIdentityM(rotation, 0);
        setIdentityM(scale, 0);

        setIdentityM(identity, 0);

        yawPitchRoll = new Vector(0.0f, 0.0f, 0.0f);

        originalScale = new Vector(0.0f, 0.0f, 0.0f);
    }

    // force the decendents to implement the specific render method
    public abstract void Render(JagaCamera camera);

    public void MoveTo(Vector position)
    {
        translateM(this.position, 0, identity, 0, position.x, position.y, position.z);

        multiplyMM(this.world, 0, this.rotation, 0, this.position, 0);
        multiplyMM(this.world, 0, this.world, 0, this.scale, 0);
    }

    public void MoveBy(Vector deltaPosition)
    {
        float[] deltaPosMatrix = new float[16];

        // TODO: i don't think this is correct...
        translateM(this.position, 0, identity, 0, deltaPosition.x, deltaPosition.y, deltaPosition.z);

        multiplyMM(this.world, 0, this.position, 0, this.rotation, 0);
        multiplyMM(this.world, 0, this.world, 0, this.scale, 0);
    }

    public void RotateTo(Vector rotate)
    {
        //rotation = Matrix.RotationYawPitchRoll(rotate.Y, rotate.X, rotate.Z);
        //world = this.scale * this.rotation * this.position;
        yawPitchRoll = new Vector(rotate.x, rotate.y, rotate.z);

        setRotateEulerM(this.rotation, 0, rotate.x, rotate.y, rotate.z);

        multiplyMM(this.world, 0, this.position, 0, this.rotation, 0);
        multiplyMM(this.world, 0, this.world, 0, this.scale, 0);
    }

    public void RotateWith(float[] rotationDeltaMatrix)
    {
        //this.world = this.scale * (this.rotation * rotate) * this.position;

        multiplyMM(this.world, 0, this.position, 0, this.rotation, 0);
        multiplyMM(this.world, 0, this.world, 0, rotationDeltaMatrix, 0);
        multiplyMM(this.world, 0, this.world, 0, this.scale, 0);
    }

    public void ScaleTo(Vector scale)
    {
        // NOTE: re-creates the scaling matrix using the original size and the specified x,y,z scale factors
        //this.scale = Matrix.Scaling(new Vector3(originalSize.X * scale.X, originalSize.Y * scale.Y, originalSize.Z * scale.Z));
        //this.world = this.scale * this.rotation * this.position;

        /*
        scaleM(this.scale, 0, scale.x, scale.y, scale.z);

        multiplyMM(this.world, 0, this.scale, 0, this.rotation, 0);
        multiplyMM(this.world, 0, this.world, 0, this.position, 0);
        */

        throw new java.lang.UnsupportedOperationException("need to resolve if this method is still needed.");
    }

    public void ScaleBy(Vector deltaScale)
    {
        scaleM(this.scale, 0, identity, 0, deltaScale.x, deltaScale.y, deltaScale.z);

        multiplyMM(this.world, 0, this.position, 0, this.rotation, 0);
        multiplyMM(this.world, 0, this.world, 0, this.scale, 0);
    }

    /**
     * Width is the size of the primitive along the X axis (M11)
     */
    public Float getWidth() { return this.scale[0]; }
    public void setWidth(Float width) { ScaleTo(new Vector(width, getHeight(), getDepth())); }

    /**
     * Height is the size of the primitive along the Y axis (M22)
     */
    public Float getHeight() { return this.scale[(1 * 4) + 1]; }
    public void setHeight(Float height) { ScaleTo(new Vector(getWidth(), height, getDepth())); }

    /**
     * Depth is the size of the primitive along the Z axis (M33)
     */
    public Float getDepth() { return this.scale[(2 * 4) + 2]; }
    public void setDepth(Float depth) { ScaleTo(new Vector(getWidth(), getHeight(), depth)); }


    /**
     * Position of the Primitive as a R3 Vector
     */
    public Vector getPosition() { return new Vector(getX(), getY(), getZ()); }
    public void setPosition(Vector pos) { MoveTo(pos); }

    /**
     * X Position of the Primitive M41
     */
    public Float getX() { return this.position[12]; }
    public void setX(Float x) { MoveTo(new Vector(x, getY(), getZ())); }

    /**
     * Y Position of the Primitive M42
     */
    public Float getY() { return this.position[13]; }
    public void setY(Float y) { MoveTo(new Vector(getX(), y, getZ())); }

    /**
     * Z Position of the Primitive M43
     */
    public Float getZ() { return this.position[14]; }
    public void setZ(Float z) { MoveTo(new Vector(getX(), getY(), z)); }


    public Float getYaw() { return yawPitchRoll.getX(); }
    public void setYaw(Float yaw) { RotateTo(new Vector(yaw, getPitch(), getRoll())); }

    public Float getPitch() { return yawPitchRoll.getY(); }
    public void setPitch(Float pitch) { RotateTo(new Vector(getYaw(), pitch, getRoll())); }

    public Float getRoll() { return yawPitchRoll.getZ(); }
    public void setRoll(Float roll) { RotateTo(new Vector(getYaw(), getPitch(), roll)); }
}
