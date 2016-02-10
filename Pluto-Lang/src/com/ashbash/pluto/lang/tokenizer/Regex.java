package com.ashbash.pluto.lang.tokenizer;

/**
 * Created by ashleychapman on 10/02/2016.
 */

public class Regex {

    public static final String IDENTIFIER = "[a-zA-Z]([a-zA-Z0-9]*)?";
    public static final String STRING_LITERAL = "\"(.*)?\"";
    public static final String DOUBLE_LITERAL = "(-)?([.])?[0-9][0-9.]*";
    public static final String FLOAT_LITERAL = DOUBLE_LITERAL + "f";
    public static final String INTEGER_LITERAL = "(-)?[0-9]*";
    public static final String BOOLEAN_LITERAL = "(true|false)";
    public static final String PROPERTY = "@" + IDENTIFIER;
    public static final String COMPARISON = "((==)|(!=)|(>=)|(<=)|>|<)";
    public static final String CONDITIONAL_OPERATOR = "(&)";
    public static final String IDENTIFIER_OR_LITERAL = "(" + IDENTIFIER + "|" + DOUBLE_LITERAL + "|" + FLOAT_LITERAL + "|" + BOOLEAN_LITERAL + "|" + STRING_LITERAL + "|" + INTEGER_LITERAL + ")";

}
