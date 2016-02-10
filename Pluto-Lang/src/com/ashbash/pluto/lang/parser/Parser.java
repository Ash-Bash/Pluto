package com.ashbash.pluto.lang.parser;

import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public abstract class Parser<T extends Block> {

    /**
     * Tokes a Line and Checks to See if it is for this parser by using regex
     */
    public abstract boolean shouldParse(String line);

    /**
     *  Toke the SuperBlock and The Tokanizer for The Line and Return a Block of this Parser's Type.
     */
    public abstract T parse(Block superBlock, Tokenizer tokenizer);
}
