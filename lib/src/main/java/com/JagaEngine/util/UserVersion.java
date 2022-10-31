package com.JagaEngine.util;

/**
 * The UserVersion class is used to specify user defined version strings and paths...
 * these values are intended to be separate from auto generated files such as
 * the SVN Version number of SVN Path. 
 */
public class UserVersion
{
    /**
     * The version number is made up of four distinct sections:
     * Major.Minor.Build.Revision
     *
     * git log --oneline | wc -l
     */
    public static String USER_VERSION_NUMBER = "6.0.0.0";
    
    /**
     * The path is the path to the application.
     *
     * TODO: figure out the GIT command for this
     * git ?
     */
    public static String USER_PATH = "-JagaEngine-";

}
