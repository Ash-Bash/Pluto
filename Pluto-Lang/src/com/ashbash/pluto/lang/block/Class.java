package com.ashbash.pluto.lang.block;

/**
 * Created by ashleychapman on 10/02/2016.
 */

/**
 * Represents a Class
 */
public class Class extends Block {

    //Variable Properties
    private String name;

    public Class(String name) {
        super(null);

        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        // Run Main Method
    }
}
