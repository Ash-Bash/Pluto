package com.ashbash.pluto.lang;

/**
 * Created by ashleychapman on 11/02/2016.
 */
public interface Type {

    public static Type match(String str) {
        try {
            return PrimitiveType.valueOf(str.toUpperCase());
        }
        catch (Exception e) {
            //TODO: Match str to a class
            return null;
        }
    }
}
