package com.ashbash.pluto.lang.block;

/**
 * Created by ashleychapman on 10/02/2016.
 */

import com.ashbash.pluto.lang.Variable;

import java.util.ArrayList;

/**
 * Represents a Black Of Code
 */
public abstract class Block {

    private Block superBlock;
    private ArrayList<Block> subBlocks;
    private ArrayList<Variable> variables;

    public Block(Block superBlock) {
        this.superBlock = superBlock;
        this.subBlocks = new ArrayList<>();
        this.variables = variables;
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public void addBlock(Block block) {
        subBlocks.add(block);
    }
    public Variable getVariable(String name) {
        // Checks the SuperBlocks first


        for (Variable v : variables) {
           if (v.getName().equals(name)) {
               return v;
           }
        }

        return null;
    }

    public void addVariable(Variable v) {
        variables.add(v);
    }

    public abstract void run();
}
