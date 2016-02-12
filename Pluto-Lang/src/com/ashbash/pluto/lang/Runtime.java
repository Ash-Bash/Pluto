package com.ashbash.pluto.lang;

import com.ashbash.pluto.lang.block.Block;
import com.ashbash.pluto.lang.block.Class;
import com.ashbash.pluto.lang.block.Function;
import com.ashbash.pluto.lang.parser.ClassParser;
import com.ashbash.pluto.lang.parser.FunctionParser;
import com.ashbash.pluto.lang.parser.Parser;
import com.ashbash.pluto.lang.parser.VariableParser;
import com.ashbash.pluto.lang.tokenizer.Tokenizer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by ashleychapman on 09/02/2016.
 */
public class Runtime {

    // Runtime Properties
    private ArrayList<Class> classes;

    private String SOURCE_CODE = "";

    private String VERSION_COMMENT_ENDLINE = "                                                //";
    private String BUILD_COMMENT_ENDLINE = "                                                     //";
    private String RUNTIME_VERSION = "0.1.0";
    private String RUNTIME_BUILD = "24";

    public Runtime() {
        CommandLinePrompt();
    }

    public void CommandLinePrompt() {
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
        try {
            Scanner input = new Scanner(System.in);
            System.out.print("Enter a .plu file: ");
            File file = new File(input.nextLine());
            //Scanner input = new Scanner(new File("src/examples/HelloWorld.plu"));

            SOURCE_CODE = new Scanner(file).useDelimiter("\\A").next();
            CodeRuntime(SOURCE_CODE);
        }
        catch (Exception e) {

        }
    }

    public void CodeRuntime(String code) {
        this.classes = new ArrayList<Class>();

        System.out.println(code);

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
                    System.out.println("Block Name: " + newBlock.getClass().getSimpleName());
                    if (newBlock instanceof Class){
                        classes.add((Class) newBlock);
                    }
                    else if (newBlock instanceof Function){
                        block.getBlockTree().get(0).addBlock(newBlock);
                    }
                    else
                    {
                        System.out.println("Adding: " + newBlock.getClass().getSimpleName() + " to " + block.getClass().getSimpleName());
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
                if (b instanceof Function) {
                    Function function = (Function) b;
                    System.out.println("Function Name: " + function.getName());
                    System.out.println("Function Type: " + function.getType());
                    System.out.println("Function Params: " + Arrays.toString(function.getParameters()));
                    if (function.getName().equals("main") && function.getType().equals("void") && function.getParameters().length == 0) {
                        main = c;
                    }

                    for (Block bl : function.getSubBlocks()) {
                        System.out.println(bl.getClass().getSimpleName());
                    }
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
