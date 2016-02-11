package com.ashbash.pluto.lang.block;

import com.ashbash.pluto.lang.Parameter;
import com.ashbash.pluto.lang.PrimitiveType;
import com.ashbash.pluto.lang.Value;
import com.ashbash.pluto.lang.Variable;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class Function extends Block {

    //Variable Properties
    private String name;
    private PrimitiveType type;
    private Parameter[] params;
    private Value returnValue;

    public Function(Block superBlock, String name, PrimitiveType type, Parameter[] params) {
        super(superBlock);

        this.name = name;
        this.type = type;
        this.params = params;
    }

    @Override
    public void run() {
        invoke();
    }

    public Value invoke(Value... values) {
        // Invokes the Function/Method with the Supplied values.

        if (values.length != params.length) {
            throw  new IllegalArgumentException("Wrong Number of Values for Parameter");
        }

        for (int i = 0; i < values.length && i < params.length; i++) {
            Parameter p = params[i];
            Value v = values[i];

            if (p.getType() != v.getType()) {
                throw new IllegalStateException("Parameter " + p.getName() + " should be " + p.getType() + " : got " + v.getType());
            }

            addVariable(new Variable(this, p.getType(), p.getName(), v.getValue()));
        }

        for (Block b : getSubBlocks()) {
            b.run();

            if (returnValue != null) {
                break;
            }
        }

        if (returnValue == null && type != PrimitiveType.VOID) {
            throw new IllegalStateException("Expected Return Value, Got None");
        }

        Value localReturnValue = returnValue;
        returnValue = null;
        return localReturnValue;
    }
}
