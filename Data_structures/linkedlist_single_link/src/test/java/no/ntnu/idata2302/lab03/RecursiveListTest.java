/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;

public class RecursiveListTest extends SequenceTest {


    //@Override
    public Sequence<Integer> emptySequence() {
        return new RecursiveList();
    }

} 
