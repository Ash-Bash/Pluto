package com.ashbash.pluto.lang.tokenizer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ashleychapman on 09/02/2016.
 */
public class Tokenizer {

    private ArrayList<TokenData> tokenDatas;

    private String str;

    private Token lastToken;
    private boolean pushBack;

    public Tokenizer(String str) {
        this.tokenDatas = new ArrayList<TokenData>();
        this.str = str;

        //Register Tokes To The Tokenizer.TokenData
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.COMPARISON + ")"), TokenType.TOKEN));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.CONDITIONAL_OPERATOR + ")"), TokenType.TOKEN));

        for (String t : new String[] { "=", "\\(", "\\)", "\\.", "\\,", "->", "\\:" }) {
            registerTokenData(new TokenData(Pattern.compile("^(" + t + ")"), TokenType.TOKEN));
        }

        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.BOOLEAN_LITERAL + ")"), TokenType.BOOL_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.IDENTIFIER + ")"), TokenType.IDENTIFIER));
        registerTokenData(new TokenData(Pattern.compile("^(//.*)"), TokenType.EMPTY));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.PROPERTY + ")"), TokenType.PROPERTY));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.STRING_LITERAL + ")"), TokenType.STRING_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.INTEGER_LITERAL + ")"), TokenType.INTEGER_LITERAL));
        registerTokenData(new TokenData(Pattern.compile("^(" + Regex.DOUBLE_LITERAL + ")"), TokenType.DOUBLE_LITERAL));

        /*tokenDatas.add(new TokenData(Pattern.compile("[a-zA-Z][a-zA-Z0-9]*"), TokenType.IDENTIFIER));
        tokenDatas.add(new TokenData(Pattern.compile("(-)?[0-9]+"), TokenType.INTEGER_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("(-)?([.])?[0-9][0-9.]*"), TokenType.DOUBLE_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("(-)?([.])?[0-9][0-9.]*"), TokenType.FLOAT_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("(true|false)"), TokenType.BOOL_LITERAL));
        tokenDatas.add(new TokenData(Pattern.compile("\" *\""), TokenType.STRING_LITERAL));*/
    }

    public Token nextToken() {
        str = str.trim();

        if (pushBack){
            pushBack = false;
            return  lastToken;
        }

        if (str.isEmpty()) {
            return (lastToken = new Token("", TokenType.EMPTY));
        }

        for (TokenData data : tokenDatas) {
            Matcher matcher = data.getPattern().matcher(str);

            if (matcher.find()) {
                String token = matcher.group().trim();
                str = matcher.replaceFirst("");

                if (data.getType() == TokenType.STRING_LITERAL) {
                    return (lastToken = new Token(token.substring(1, token.length() - 1), TokenType.STRING_LITERAL));
                }
                else {
                    return (lastToken = new Token(token, data.getType()));
                }
            }
        }
        throw new IllegalStateException("Could not parse " + str);
    }

    public boolean hasNextToken() {
        return !str.isEmpty();
    }

    public void pushBack() {
        if (lastToken != null) {
            this.pushBack = true;
        }
    }

    public void registerTokenData(TokenData data) {
        tokenDatas.add(data);
    }
}
