package com.ashbash.pluto.lang.tokenizer;

/**
 * Created by ashleychapman on 09/02/2016.
 */
public enum TokenType {

    /**
     * A Token For example, ( ) - = * / .
     */
    TOKEN,
    /**
     * A Identifier: First character is a letter, any proceeding characters are letters or numbers
     */
    IDENTIFIER,
    /**
     * A Whole Number: 101
     */
    INTEGER_LITERAL,
    /**
     * A Number with a decimal place: 3.1456789097654
     */
    DOUBLE_LITERAL,
    /**
     * A Number with a decimal place: 3.14f
     */
    FLOAT_LITERAL,
    /**
     * A True Or False value
     */
    BOOL_LITERAL,
    /**
     * Anything enclosed in double quotes "Hello" "1"
     */
    STRING_LITERAL,
    /**
     * A Number from 0 to 255
     */
    BYTE_LITERAL,
    /**
     * Any Character enclosed in ' - 'C'
     */
    CHAR_LITERAL,



    PROPERTY,



    EMPTY
}
