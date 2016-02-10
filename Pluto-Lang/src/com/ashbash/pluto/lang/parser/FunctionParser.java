package com.ashbash.pluto.lang.parser;

import com.ashbash.pluto.lang.Parameter;
import com.ashbash.pluto.lang.PrimitiveType;
import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.block.Function;
import com.ashbash.pluto.lang.tokenizer.Token;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;

import java.util.ArrayList;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class FunctionParser extends Parser<Function> {

    @Override
    public boolean shouldParse(String line) {
        return line.matches("function [a-zA-Z][a-zA-Z0-9]* (requires|optional) \\(([a-zA-Z][a-zA-Z0-9]*: [a-zA-Z][a-zA-Z0-9]*)*\\) returns [a-zA-Z][a-zA-Z0-9]*");
    }

    @Override
    public Function parse(Block superBlock, Tokenizer tokenizer) {
        tokenizer.nextToken(); // Skips the Function Token

        String name = tokenizer.nextToken().getToken();

        tokenizer.nextToken(); // Skips the Requires Or Optional Token
        tokenizer.nextToken(); // Skips the ( Token.

        Token first = tokenizer.nextToken();

        ArrayList<Parameter> params = new ArrayList<>();

        if (!first.getToken().equals(")")) {
            String[] paramData = new String[] { first.getToken(), null}; // 0 - value, 1 - type

            while (tokenizer.hasNextToken()) {
                Token token = tokenizer.nextToken();

                if (token.getToken().equals(")")) {
                    break;
                }

                if (paramData[0] == null) {
                    paramData[0] = token.getToken();
                }
                else {
                    paramData[1] = token.getToken();
                    params.add(new Parameter(PrimitiveType.valueOf(paramData[1].toUpperCase()), paramData[0]));
                    paramData = new String[2];
                }
            }
        }

        tokenizer.nextToken(); // Skips returns token.

        PrimitiveType returnType = PrimitiveType.valueOf(tokenizer.nextToken().getToken().toUpperCase());

        return new Function(superBlock, name, returnType, params.toArray(new Parameter[params.size()]));
    }
}
