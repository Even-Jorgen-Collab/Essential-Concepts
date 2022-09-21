/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;



public abstract class Sequence<T> {

    /**
     * @return the length of the sequence as a positive integer.
     */
    public abstract int length();

    /**
     * @return the item positioned at the given index
     */
    public abstract T get(int index) throws InvalidIndex;

    /**
     * Place the given item at the given index.
     */
    public abstract void set(int index, T item) throws InvalidIndex;

    /**
     * Insert a element at the given position. Other elements will be
     * shift forward the end.
     */
    public abstract void insert(int index, T item) throws InvalidIndex;

    /**
     * Remove the item placed at the given index. Element with greater
     * indices will be shifted towards the front of the sequence.
     */
    public abstract void remove(int index) throws InvalidIndex;

    /**
     * @return the index of an occurrence of the given item.
     */
    public abstract int search(T item);

    /**
     * @return true is the list is empty (i.e., if the length is zero)
     */
    public boolean isEmpty() {
        return length() == 0;
    }

    /**
     * Insert at the front of the list
     */
    public void prepend(T item) {
        try {
            insert(1, item);

        } catch (InvalidIndex error) {
            throw new RuntimeException("Invalid list state.", error);

        }
    }

    /**
     * Insert at the back of the list
     */
    public void append(T item) {
        try {
            insert(length() + 1, item);

        } catch (InvalidIndex error) {
            throw new RuntimeException("Invalid list state.", error);

        }
    }

}
