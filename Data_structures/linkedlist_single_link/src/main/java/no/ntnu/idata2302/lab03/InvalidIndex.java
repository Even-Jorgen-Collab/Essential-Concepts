/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;


class InvalidIndex extends Exception {

    private int index;

    public InvalidIndex(int index) {
        super();
        this.index = index;
    }

}
