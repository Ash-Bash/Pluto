package com.ashbash.pluto.lang.parser;

import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;
import com.ashbash.pluto.lang.block.Class;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class ClassParser extends Parser<Class> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("class [a-zA-Z][a-zA-Z0-9]*");
    }

    @Override
    public Class parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken(); // Skip the Class token

        String name = tokenizer.nextToken().getToken(); // Gets the String Value of the Next Token

        return new Class(name);
    }
}
