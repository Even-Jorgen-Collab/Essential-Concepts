/*
 * This file is part of NTNU's IDATA2302 Lab 03.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;

import java.util.Arrays;
import java.util.Objects;

public class WarmUp {

    public static void throwEx() {
        throw new IllegalArgumentException();
    }

    /**
     * Create a recursive algorithm that takes a value and its
     * exponent and returns this value raised to the given exponent.
     */
    public static int power(int value, int exponent) {
        if (value < 0 || exponent < 0) { throwEx();} 
        if (exponent == 0) { return 1;}

        return power(value, exponent-1)*value;
    }


    /**
     * Compute the sum of the given array. For instance, sum([1, 2,
     * 3]) = 6.
     */
    public static int sum(int[] array) {
        if (array == null) { throwEx();}
        if (array.length == 0) { return 0; }
        if (array.length == 1) { return array[0]; }
        array[0] += array[array.length-1];
        return sum(Arrays.copyOfRange(array,0,array.length-1));
    }


    /**
     * Check if the given text is a palindrome, that is a symmetrical
     * sequence of letters, such as 'kayak' or 'madam'.
     */
    public static boolean isPalindrome(String text) {
        if (text == null) { throwEx();}
        if (text.length() <= 1 ) {return true;}
        if (Objects.equals(text.charAt(0), text.charAt(text.length()-1))) { 
            isPalindrome(text.substring(1, text.length()-1));
        } else {return false;}
        return true;
    }



    /**
     * Convert the given number into the given base. The result is a
     * string (a sequence of symbols), because base greater than 10
     * will requires more symbols than there are Arabic digits.
     */
    public static String toBase(int number, int base) {
        
        int next = number / base;
        int remainder = number % base;
        
        if (next < 1) {return SYMBOLS[remainder];}
        return toBase(next, base) + SYMBOLS[remainder];
    }

    private static final String[] SYMBOLS = new String[]{
        "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
        "A", "B", "C", "D", "E", "F", "G", "H", "G", "I"
    };
}
