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


public class ArraySumTest
{

    @Test
    public void sumOfEmptyIsZero() {
        assertEquals(0, WarmUp.sum(new int[]{}));
    }

    @Test
    public void sumOfThreeOnesIsThree() {
        assertEquals(3, WarmUp.sum(new int[]{1,1,1}));
    }

    @Test
    public void sumOfThreeZerosIsZero() {
        assertEquals(0, WarmUp.sum(new int[]{0,0,0}));
    }

    @Test
    public void sumOfNFirstIntegers() {
        // 1 + 2 + ... + n = [n * (n+1)]/ 2
        assertEquals(55, WarmUp.sum(new int[]{1,2,3,4,5,6,7,8,9,10}));
    }

    @Test
    public void sumWithNegativeNumbers() {
        assertEquals(-5, WarmUp.sum(new int[]{4, 6, -10, -5}));
    }

    @Test(expected=IllegalArgumentException.class)
    public void sumOfNullRaisesAnException() {
        WarmUp.sum(null);
    }



}
