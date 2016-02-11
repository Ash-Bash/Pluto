package com.ashbash.pluto.lang;

import com.ashbash.pluto.lang.block.Block;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class Variable extends Value {

    //Variable Properties
    private Block block;
    private String name;

    public Variable(Block block, Type type, String name, Object value) {
        super(type, value);

        this.block = block;
        this.name = name;
    }

    public Block getBlock() {
        return block;
    }

    public String getName() {
        return name;
    }
}
