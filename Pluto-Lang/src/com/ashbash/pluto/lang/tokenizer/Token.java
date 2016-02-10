package com.ashbash.pluto.lang.tokenizer;

/**
 * Created by ashleychapman on 09/02/2016.
 */
public class Token {

    private String token;
    private TokenType type;

    public Token(String token, TokenType type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public TokenType getType() {
        return type;
    }
}
