package com.ashbash.pluto.lang;

/**
 * Created by ashleychapman on 10/02/2016.
 */

/**
 * Represents a Value
 */
public class Value {

    //Variable Properties
    private Type type;
    private Object value;

    public Value(Type type, Object value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
