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


public class BaseConversionTest
{
    @Test
    public void fineInBaseSixIsFive() {
        assertEquals("5", WarmUp.toBase(5, 6));
    }

    @Test
    public void sixInBaseFixeIsEleven() {
        assertEquals("11", WarmUp.toBase(6, 5));
    }

    @Test
    public void tenInBaseFourIs22() {
        assertEquals("22", WarmUp.toBase(10, 4));
    }

    @Test
    public void oneHundredInBaseFiveIsFourHundred() {
        assertEquals("400", WarmUp.toBase(100, 5));
    }

    @Test
    public void oneHundredInBaseThreeIs10210() {
        assertEquals("10201", WarmUp.toBase(100, 3));
    }

    @Test
    public void beef() {
        assertEquals("BEEF", WarmUp.toBase(48879, 16));
    }


}
