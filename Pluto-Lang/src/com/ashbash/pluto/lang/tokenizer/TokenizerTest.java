package com.ashbash.pluto.lang.tokenizer;

/**
 * Created by ashleychapman on 10/02/2016.
 */
public class TokenizerTest {

    public static void main(String[] args) {
        String code = "class HelloWorld\n" +
                "\n" +
                "    function main requires () returns void\n" +
                "        print(\"Hello, World!\") \n" +
                "var foo : String = \"Test String\"";

        Tokenizer tokenizer = new Tokenizer(code);

        while (tokenizer.hasNextToken()) {
            System.out.println(tokenizer.nextToken().getToken());
        }

    }


}
