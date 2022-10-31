package com.JagaEngine;

import com.JagaEngine.util.PropertyInvoker;

import org.junit.Test;
import static org.junit.Assert.*;

public class PropertyInvoker_Test
{
    public class TestType
    {
        public float position;

        // NOTE: it is important here that the get and set values operate on the class type and not the primitive type.
        public Float getPosition() { return position; }
        public void setPosition(Float value) { position = value; }

        public TestType()
        {
            position = 1.0f;
        }
    }

    public class PropertyInvoker_unitTest<T> extends PropertyInvoker
    {
        public PropertyInvoker_unitTest(String property, Object obj, Class<T> classType) throws NoSuchMethodException
        {
            super(property, obj, classType);
        }

        public java.lang.reflect.Method getterMethod() { return getMethod; };
        public java.lang.reflect.Method setterMethod() { return setMethod; };
        public Object getObj() { return obj; };
    }

    @Test
    public void ctor_Test() throws Exception
    {
        TestType testPosition = new TestType();

        PropertyInvoker_unitTest<Float> pi = new PropertyInvoker_unitTest<Float>("Position", testPosition, Float.class);

        assertEquals(pi.getObj(), testPosition);

        assertEquals(pi.getterMethod().getName(), "getPosition");
        assertEquals(pi.setterMethod().getName(), "setPosition");
    }

    @Test
    public void getPosition_Test() throws Exception
    {
        TestType testPosition = new TestType();

        PropertyInvoker_unitTest<Float> pi = new PropertyInvoker_unitTest<Float>("Position", testPosition, Float.class);

        assertEquals(1.0f, (float)pi.getValue(), 0.001f);

        assertEquals(1.0f, (float)testPosition.getPosition(), 0.001f);
    }

    @Test
    public void setPosition_Test() throws Exception
    {
        TestType testPosition = new TestType();

        PropertyInvoker_unitTest<Float> pi = new PropertyInvoker_unitTest<Float>("Position", testPosition, Float.class);

        pi.setValue(3.14159f);
        assertEquals(3.14159f, (float)pi.getValue(), 0.001f);

        assertEquals(3.14159f, testPosition.getPosition(), 0.001f);
    }

    // TODO: ctor throw Exception for finding the getter

    // TODO: ctor throw Exception for finding the setter
}
