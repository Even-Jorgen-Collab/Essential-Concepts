/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */

package no.ntnu.idata2302.lab03;


public class Benchmark {


    public static <T> int benchmark(Sequence<T> sequence, int maximum, T dummyItem) {
        var index = 0;
        try {
            while (index < maximum) {
                sequence.append(dummyItem);
                index++;
            }

        } catch (StackOverflowError error) {

        }
        return index;
    }

    public static void main(String[] args) {
        final int MAXIMUM = 100000;
        int iterationLimit = benchmark(new IterativeList<Integer>(null), MAXIMUM, 55);
        var error = (iterationLimit < MAXIMUM) ? "(error)" : "";
        System.out.printf("Iterative List: %d item(s) inserted. %s\n", iterationLimit, error);

        int recursionLimit = benchmark(new RecursiveList<Integer>(), MAXIMUM, 55);
        error = (recursionLimit < MAXIMUM) ? "(error)" : "";
        System.out.printf("Recursive List: %d item(s) inserted. %s\n", recursionLimit, error);
    }

}
