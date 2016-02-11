package com.ashbash.pluto.lang;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class Parameter {

    //Variable Properties
    private String name;
    private Type type;

    public Parameter(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
