/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class SequenceTest
{

    private Sequence<Integer> sequence;

    public abstract Sequence<Integer> emptySequence();

    public Sequence<Integer> fromArray(Integer... array) {
        var sequence = emptySequence();
        for(var each: array) {
            sequence.append(each);
        }
        return sequence;
    }

    @Before
    public void setUp() {
        this.sequence = fromArray(1,2,3,4);
    }


    @Test
    public void emptySequenceHasLengthZero() {
        var sequence = emptySequence();
        assertEquals(0, sequence.length());
        assertTrue(sequence.isEmpty());
    }

    @Test
    public void getReturnsTheItemAtIndex () throws InvalidIndex {
        assertEquals((Integer) 3, sequence.get(3));
    }

    @Test(expected=InvalidIndex.class)
    public void getRejectsZeroIndex () throws InvalidIndex {
        sequence.get(0);
    }

    @Test(expected=InvalidIndex.class)
    public void getRejectsIndexBeyondLength () throws InvalidIndex {
        sequence.get(5);
    }

    @Test
    public void setUpdateItemByIndex () throws InvalidIndex {
        sequence.set(3, 0);
        assertEquals((Integer) 0, sequence.get(3));
    }

    @Test
    public void insertIncreaseLengthByOne() throws InvalidIndex {
        var length = sequence.length();
        sequence.insert(1, 0);
        assertEquals(length+1, sequence.length());
    }

    @Test
    public void insertShiftsOnlySubsequentItemsTowardsTheEnd() throws InvalidIndex {
        sequence.insert(3, 0);
        assertEquals((Integer) 2, sequence.get(2));
        assertEquals((Integer) 0, sequence.get(3));
        assertEquals((Integer) 3, sequence.get(4));
    }

    @Test
    public void appendAddsAnItemAtTheBack() throws InvalidIndex {
        sequence.append(5);
        assertEquals((Integer) 5, sequence.get(5));
    }

    @Test
    public void appendingFromScratchShouldBuildaList() throws InvalidIndex {
        var sequence = emptySequence();
        for(int i=1 ; i<5; i++) {
            sequence.append(i);
        }
        assertEquals((Integer) 1, sequence.get(1));
        assertEquals((Integer) 2, sequence.get(2));
        assertEquals((Integer) 3, sequence.get(3));
        assertEquals((Integer) 4, sequence.get(4));
    }

    @Test
    public void prependingFromScratchShouldReverseTheList() throws InvalidIndex {
        var sequence = emptySequence();
        for(int i=1 ; i<5; i++) {
            sequence.prepend(i);
        }
        assertEquals((Integer) 4, sequence.get(1));
        assertEquals((Integer) 3, sequence.get(2));
        assertEquals((Integer) 2, sequence.get(3));
        assertEquals((Integer) 1, sequence.get(4));
    }

    @Test
    public void removeShiftsOnlySubsequentItemsTowardsTheFront() throws InvalidIndex {
        sequence.remove(3);
        assertEquals((Integer) 2, sequence.get(2));
        assertEquals((Integer) 4, sequence.get(3));
        assertEquals(3, sequence.length());
    }

    @Test
    public void searchReturnsZeroWhenNotFound () {
        var index = sequence.search(7);
        assertEquals(0, index);
    }


    @Test
    public void searchReturnsAnIndexWhereItemIs () throws InvalidIndex {
        var index = sequence.search(4);
        assertEquals((Integer) 4, sequence.get(index));
    }

}