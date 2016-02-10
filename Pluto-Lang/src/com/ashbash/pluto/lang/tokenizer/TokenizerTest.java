package com.ashbash.pluto.lang.tokenizer;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Created by ashleychapman on 10/02/2016.
 */
public class TokenizerTest {

    public static void main(String[] args) {
        String code = "class HelloWorld\n" +
                "\n" +
                "    function main requires () returns void\n" +
                "        print(\"Hello, World!\")";

        Tokenizer tokenizer = new Tokenizer(code);

        while (tokenizer.hasNextToken()) {
            System.out.println(tokenizer.nextToken().getToken());
        }

    }


}
