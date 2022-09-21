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


public class PowerTest
{

    @Test
    public void powerZeroIsOne() {
        assertEquals(1, WarmUp.power(25, 0));
    }

    @Test
    public void powerOneIsTheGivenNumber() {
        assertEquals(25, WarmUp.power(25, 1));
    }

    @Test
    public void powerTwoIsSquared() {
        assertEquals(4*4, WarmUp.power(4,2));
    }

    @Test
    public void powerFour() {
        assertEquals(4*4*4*4, WarmUp.power(4, 4));
    }

    @Test(expected=IllegalArgumentException.class)
    public void rejectNegativeExponents() {
        WarmUp.power(4, -4);
    }


}
