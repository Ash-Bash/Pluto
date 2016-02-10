package com.ashbash.pluto.lang.parser;

import com.ashbash.pluto.lang.PrimitiveType;
import com.ashbash.pluto.lang.Variable;
import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.tokenizer.Token;
import com.ashbash.pluto.lang.tokenizer.TokenType;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class VariableParser extends Parser<Block> {
    @Override
    public boolean shouldParse(String line) {
        return line.matches("(var|let) [a-zA-Z]+ : [a-zA-Z]+ = \"?[a-zA-Z0-9]\"?");
    }

    @Override
    public Block parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken(); //Skips the Var or Let token

        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken(); // Skips the : token

        PrimitiveType type = PrimitiveType.valueOf(tokenizer.nextToken().getToken().toUpperCase());

        tokenizer.nextToken(); // Skips The = Token

        Token v = tokenizer.nextToken();

        Object value = null;

        if (v.getType() == TokenType.INTEGER_LITERAL) {
            value = Integer.valueOf(v.getToken());
        }
        else if (v.getType() == TokenType.DOUBLE_LITERAL) {
            value = Double.valueOf(v.getToken());
        }
        else if (v.getType() == TokenType.FLOAT_LITERAL) {
            value = Float.valueOf(v.getToken());
        }
        else if (v.getType() == TokenType.BOOL_LITERAL) {
            value = Boolean.valueOf(v.getToken());
        }
        else if (v.getType() == TokenType.STRING_LITERAL) {
            value = v.getToken();
        }
        else /* If it's an Identifier */{
            // This is an identifier; we need to get its value
            value = superBlock.getVariable(v.getToken()).getValue();
        }

        // Add this Variable to the Block
        superBlock.addVariable(new Variable(superBlock, type, name, value));
        return null;
    }
}
