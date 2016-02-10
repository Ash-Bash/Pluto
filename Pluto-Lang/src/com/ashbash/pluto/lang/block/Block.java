package com.ashbash.pluto.lang.block;

/**
 * Created by ashleychapman on 10/02/2016.
 */

import java.util.ArrayList;

/**
 * Represents a Black Of Code
 */
public abstract class Block {

    private Block superBlock;
    private ArrayList<Block> subBlock;

    public Block(Block superBlock) {
        this.superBlock = superBlock;
        this.subBlock = new ArrayList<>();
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public abstract void run();
}
