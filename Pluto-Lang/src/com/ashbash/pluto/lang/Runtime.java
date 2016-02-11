package com.ashbash.pluto.lang;

import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.block.Class;
import com.ashbash.pluto.lang.block.Function;
import com.ashbash.pluto.lang.parser.ClassParser;
import com.ashbash.pluto.lang.parser.FunctionParser;
import com.ashbash.pluto.lang.parser.Parser;
import com.ashbash.pluto.lang.parser.VariableParser;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;

import java.util.ArrayList;

/**
 * Created by ashleychapman on 09/02/2016.
 */
public class Runtime {

    // Runtime Properties
    private ArrayList<Class> classes;

    private String VERSION_COMMENT_ENDLINE = "                                                //";
    private String BUILD_COMMENT_ENDLINE = "                                                     //";
    private String RUNTIME_VERSION = "0.1.0";
    private String RUNTIME_BUILD = "24";

    public Runtime() {

        for (int i = 0; i < RUNTIME_VERSION.length(); i++)
        {
            VERSION_COMMENT_ENDLINE.replaceFirst("\\s+$", "");
        }
        for (int i = 0; i < RUNTIME_BUILD.length(); i++)
        {
            BUILD_COMMENT_ENDLINE.replaceFirst("\\s+$", "");
        }

        System.out.println("///////////////////////////////////////////////////////////////////");
        System.out.println("//$Pluto-Lang Runtime                                            //");
        System.out.println("//$Version: " + RUNTIME_VERSION + VERSION_COMMENT_ENDLINE);
        System.out.println("//$Build: " + RUNTIME_BUILD + BUILD_COMMENT_ENDLINE);
        System.out.println("///////////////////////////////////////////////////////////////////");

        this.classes = new ArrayList<Class>();

        String code = "class HelloWorld\n" +
                "\n" +
                "    function main requires () returns void\n" +
                "        print(\"Hello, World!\") \n" +
                "        var foo : String = \"Test String\"";

        Parser<?>[] parsers = new Parser<?>[] { new ClassParser(), new FunctionParser(), new VariableParser() };

        Class main = null;

        Block block = null;

        boolean success = false;

        for (String line : code.split("\n")) {
            line = line.trim();
            Tokenizer tokenizer = new Tokenizer(line);

            for (Parser<?> parser : parsers) {
                if (parser.shouldParse(line)) {
                    Block newBlock = parser.parse(block, tokenizer);

                    if (newBlock instanceof Class){
                        classes.add((Class) newBlock);
                    }
                    else
                    {
                        block.addBlock(newBlock);
                    }

                    block = newBlock;
                    success = true;
                    break;
                }
            }

            if (!success) {
                throw new IllegalArgumentException("Invalid line " + line);
            }
        }

        for (Class c : classes) {
            for (Block b : c.getSubBlocks()) {
                System.out.println("Found block of type: " + b.getClass().getSimpleName());
                Function function = (Function) b;
                if (function.getName().equals("main") && function.getType().equals("void") && function.getParameters().length == 0) {
                    main = c;
                }
            }
        }

        if (main == null) {
            throw new IllegalArgumentException("No Main Function.");
        }
        else
        {
            System.out.println("Found Main Function");
        }

        main.run();

        System.out.println("Main Class is Named: " + main.getName());
    }

    public static void main(String[] args) {
        new Runtime();
    }
}
