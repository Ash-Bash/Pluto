package com.ashbash.pluto.lang;

/**
 * Created by ashleychapman on 10/02/2016.
 */

/**
 * Represents a Value
 */
public class Value {

    //Variable Properties
    private PrimitiveType type;
    private Object value;

    public Value(PrimitiveType type, Object value) {
        this.type = type;
        this.value = value;
    }

    public PrimitiveType getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
