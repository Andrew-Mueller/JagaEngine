package com.JagaEngine.util;

// TODO: replace these with interfaces and specific data
// delegate int ReturnValueDelegate()
// delegate int SetValueDelegate()

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import static android.R.attr.type;

/**
 * PropertyInvoker is a helper class that uses reflection to allow sending Properties as reference
 * types.
 * @param <T> Data type of the property we are after.
 */
public class PropertyInvoker<T>
{
    protected java.lang.reflect.Method getMethod;
    protected java.lang.reflect.Method setMethod;
    protected java.lang.reflect.Method handleMethod;

    protected Object obj;

    protected Class<T> classType;

    public PropertyInvoker(String basePropertyName, Object obj, Class<T> classType) throws NoSuchMethodException
    {
        this.obj = obj;
        this.classType = classType;

        getMethod = null;
        setMethod = null;
        handleMethod = null;

        // cache the object name for throwing exceptions.
        String objName = obj.toString();

        // find the getter by looking for the method named "getbasePropertyName".
        try
        {
            getMethod = obj.getClass().getMethod("get" + basePropertyName);
        }
        catch (NoSuchMethodException ex)
        {
            getMethod = null;
            setMethod = null;

            this.obj = null;

            throw new NoSuchMethodException("get" + basePropertyName + " does not exist for object " + objName);
        }

        // find the setter by looking for the method named "setbasePropertyName"
        try
        {
            // we need to pass the T type as the second argument to the set method
            Class[] cArgs = new Class[1];
            cArgs[0] = classType;

            setMethod = obj.getClass().getMethod("set" + basePropertyName, cArgs);

            // TODO: attempt to find the set method again using a basic type instead of the object type.
            // if the basic type for the class fails, can possibly try to utilize getPrimitiveClass
            // to find the primitive type for the same class.
            //(Class<Byte>) Class.getPrimitiveClass("byte")
        }
        catch (NoSuchMethodException ex)
        {
            getMethod = null;
            setMethod = null;

            this.obj = null;

            throw new NoSuchMethodException("set" + basePropertyName + " does not exist for object " + objName);
        }

        // finally, if the object is a signaler, hook up the signal method
        try
        {
            Class[] cArgs = new Class[2];
            cArgs[0] = ISubscriber.class;
            cArgs[1] = List.class;

            handleMethod = obj.getClass().getMethod("addSubscriber", cArgs);
        }
        catch (NoSuchMethodException ex)
        {
            handleMethod = null;
            throw new NoSuchMethodException("addSubscriber does not exist for object " + objName);
        }
    }

    public T getValue() throws IllegalAccessException, InvocationTargetException
    {
        Object retVal = null;

        if (null != getMethod)
        {
            retVal = getMethod.invoke(this.obj);
        }

        return (T)retVal;
    }

    public void setValue(T value) throws IllegalAccessException, InvocationTargetException
    {
        if (null != setMethod)
        {
            setMethod.invoke(this.obj, value);
        }
    }

    public void addSubscriber(ISubscriber value) throws IllegalAccessException, InvocationTargetException
    {
        if (null != handleMethod)
        {
            // TODO: second parameter of "list of signals" needs to be sorted.
            handleMethod.invoke(this.obj, value, null);
        }
    }

    public String toString()
    {
        String getterName = "";

        if (null != getMethod)
        {
            getterName = getMethod.getName() + " and " + setMethod.getName();
        }

        return getterName;
    }
}
