package com.ashbash.pluto.lang.block;

import com.ashbash.pluto.lang.PrimitiveType;
import com.ashbash.pluto.lang.Type;
import com.ashbash.pluto.lang.Variable;

/**
 * Created by ashleychapman on 11/02/2016.
 */
public class VariableBlock extends Block {

    //Variable Block Properties
    private String type;
    private String name;
    private Object value;

    public VariableBlock(Block superBlock, String type, String name, Object value) {
        super(superBlock);

        this.type = type;
        this.name = name;
        this.value = value;
    }

    @Override
    public void run() {
        Type t = Type.match(type);

        if (t == PrimitiveType.VOID) {
            throw new IllegalStateException("Cannot Declare Void as a Variable Type");
        }

        getSuperBlock().addVariable(new Variable(getSuperBlock(), t, name, value));
    }
}
