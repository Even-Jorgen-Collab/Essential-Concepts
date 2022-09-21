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


public class PalindromeTest
{

    @Test
    public void withKayak() {
        assertTrue(WarmUp.isPalindrome("kayak"));
    }

    @Test
    public void withMadam() {
        assertTrue(WarmUp.isPalindrome("madam"));
    }

    @Test
    public void withRedivider() {
        assertTrue(WarmUp.isPalindrome("redivider"));
    }

    @Test
    public void withRaceCar() {
        assertTrue(WarmUp.isPalindrome("racecar"));
    }

    @Test
    public void withAlgorithm() {
        assertFalse(WarmUp.isPalindrome("algorithm"));
    }

    @Test
    public void withNTNU() {
        assertFalse(WarmUp.isPalindrome("ntnu"));
    }

    @Test(expected=IllegalArgumentException.class)
    public void rejectNullString () {
        WarmUp.isPalindrome(null);
    }


}
