package com.ashbash.pluto.lang.block;

import com.ashbash.pluto.lang.Parameter;
import com.ashbash.pluto.lang.PrimitiveType;
import com.ashbash.pluto.lang.Value;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class Function extends Block {

    //Variable Properties
    private String name;
    private PrimitiveType type;
    private Parameter[] params;
    private Value returnValue;

    public Function(Block superBlock) {
        super(superBlock);
    }

    @Override
    public void run() {

    }
}
