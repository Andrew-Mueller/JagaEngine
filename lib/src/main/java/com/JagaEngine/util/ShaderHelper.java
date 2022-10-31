package com.JagaEngine.util;

import static android.opengl.GLES20.*;
import android.util.Log;

public class ShaderHelper
{
    private static final String TAG = "ShaderHelper";
    
    public static int compileVertexShader(String shaderCode)
    {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }
    
    public static int compileFragmentShader(String shaderCode)
    {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }
    
    private static int compileShader(int type, String shaderCode)
    {
        // create the new shader object and verify the success.
        final int shaderObjectId = glCreateShader(type);
        
        if (0 == shaderObjectId)
        {
            if (LoggerConfig.ON)
            {
                Log.w(TAG, "compileShader.  Could not create new shader.");
            }
            
            return 0;
        }
        else
        {
            // upload the shader source code into the shader object.
            glShaderSource(shaderObjectId, shaderCode);
            
            // compile the shader.
            glCompileShader(shaderObjectId);
            
            // retrieve the compilation status
            final int[] compileStatus = new int[1];
            glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);
            
            // log the shader info log
            if (LoggerConfig.ON)
            {
                // Print the shader info log to the Android log output.
                Log.v(TAG, "Results of compiling source:" + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(shaderObjectId));
            }
            
            // check to see if the compilation was successful
            if (0 == compileStatus[0])
            {
                // If it failed, delete the shader object.
                glDeleteShader(shaderObjectId);
                
                if (LoggerConfig.ON)
                {
                    Log.w(TAG, "Compilation of shader failed.");
                }
                
                return 0;
            }
        }
        
        return shaderObjectId;
    }
    
    public static int linkProgram (int vertexShaderId, int fragmentShaderId)
    {
        // create the OpenGL ES program.
        final int programObjectId = glCreateProgram();
        
        // log the failure of the program creation.
        if (0 == programObjectId)
        {
            if (LoggerConfig.ON)
            {
                Log.w(TAG, "linkProgram. Could not create new program");
            }
        }
        else
        {
            // attach the shaders to the "OpenGL ES program".
            glAttachShader (programObjectId, vertexShaderId);
            glAttachShader (programObjectId, fragmentShaderId);
            
            // link the shaders together
            glLinkProgram(programObjectId);
            
            // verify the link of the shaders into a single program.
            final int[] linkStatus = new int[1];
            glGetProgramiv (programObjectId, GL_LINK_STATUS, linkStatus, 0);
            
            // log the status of the shader linking
            if (LoggerConfig.ON)
            {
                // Print the program info log to the Android log output.
                Log.v(TAG, "linkProgram. Resuilts of linking program:\n" + glGetProgramInfoLog(programObjectId));
            }
            
            if (0 == linkStatus[0])
            {
                //If it failed, delete the program object.
                glDeleteProgram(programObjectId);
                
                if (LoggerConfig.ON)
                {
                    Log.w(TAG, "linkProgram. Linking of program failed.");
                }
                
                return 0;
            }
        }
        
        return programObjectId;
    }
    
    public static boolean validateProgram (int programObjectId)
    {
        glValidateProgram (programObjectId);
        
        final int[] validateStatus = new int[1];
        glGetProgramiv(programObjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        Log.v(TAG, 
              "Results of validating program: " + validateStatus[0] + 
              "\nLog:" + glGetProgramInfoLog(programObjectId));
        
        return validateStatus[0] != 0;
    }

    public static int buildProgram(String vertexShaderSource, String fragmentShaderSource)
    {
        int program;

        // Compile the shaders.
        int vertexShader = compileVertexShader(vertexShaderSource);
        int fragmentShader = compileFragmentShader(fragmentShaderSource);

        // Link them into a shader program.
        program = linkProgram(vertexShader, fragmentShader);

        if (LoggerConfig.ON)
        {
            validateProgram(program);
        }

        return program;
    }
}
