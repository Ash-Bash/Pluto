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
    private ArrayList<Block> subBlocks;

    public Block(Block superBlock) {
        this.superBlock = superBlock;
        this.subBlocks = new ArrayList<>();
    }

    public Block getSuperBlock() {
        return superBlock;
    }

    public void addBlock(Block block) {
        subBlocks.add(block);
    }

    public abstract void run();
}
