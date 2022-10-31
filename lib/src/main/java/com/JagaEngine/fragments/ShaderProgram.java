package com.JagaEngine.fragments;

import static android.opengl.GLES20.*;

import android.content.Context;
import java.util.ArrayList;

import com.JagaEngine.util.TextResourceReader;
import com.JagaEngine.util.ShaderHelper;

public class ShaderProgram
{
    // uniform constants
    protected static final String U_MATRIX = "u_Matrix";
    protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
    protected static final String U_COLOR = "u_Color";


    // Attribute constants
    protected static final String A_POSITION = "a_Position";
    protected static final String A_COLOR = "a_Color";
    protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";

    // Shader program resource index
    protected final int program;

    protected ArrayList uniformIndexes;
    protected ArrayList attribIndexes;

    public static final int EMPTY_SHADER_TYPE = 0;

    protected int shaderType;

    public int getShaderType()
    {
        return shaderType;
    }

    protected ShaderProgram(Context context, int vertexShaderResourceId, int fragmentShaderResourceId)
    {
        shaderType = EMPTY_SHADER_TYPE;

        // Compile the shaders and link the program.
        program = ShaderHelper.buildProgram(TextResourceReader.readTextFileFromResource(context, vertexShaderResourceId),
                                            TextResourceReader.readTextFileFromResource(context, fragmentShaderResourceId));
    }

    public void loadResourceFile()
    {

    }

    public void useProgram()
    {
        // Set the current OpenGL shader program to this program.
        glUseProgram(program);
    }

}
