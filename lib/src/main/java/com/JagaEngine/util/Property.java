package com.JagaEngine.util;

//import java.util.function.Function;
//import java.util.function.Supplier;

public class Property<T>
{
    protected T target;

    public T get() { return target; }
    public void set(T value) { target = value; }

    /*private Supplier<T> getter;
    private final Function<T, T> setter;

    public Property(Supplier<T> getter, Function<T, T> setter)
    {
        this.getter = getter;
        this.setter = setter;
    }

    public interface PropertyBuilder<T>
    {
        public Property<T> set(Function<T, T> setter);

        public Readonly<T> readonly();
    }

    public T set(T value) {
        return setter.apply(value);
    }

    public T get() {
        return getter.get();
    }

    public Named<T> named() {
        return new GuessesName<T>(this.getter, this.setter);
    }


    public Named<T> named(String name) {
        return new ExplicitName<T>(this.getter, this.setter, name);
    }*/
}
