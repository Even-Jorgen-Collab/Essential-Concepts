/*
 * This file is part of NTNU's IDATA2302 Lab 03.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;


/**
 * This class acts as an adapter that adjusts the API of the recursive
 * type to the sequence interface.
 */
public class RecursiveList<T> extends  Sequence<T> {

    private List<T> head;

    public RecursiveList () {
        super();
        this.head = new Empty<T>();
    }

    @Override
    public int length() {
        return head.length();
    }

    @Override
    public boolean isEmpty () {
        return length() == 0;
    }

    @Override
    public T get(int index) throws InvalidIndex {
        return head.get(index);
    }

    @Override
    public void set(int index, T item) throws InvalidIndex {
        head.set(index, item);
    }

    @Override
    public void insert(int index, T item) throws InvalidIndex {
        head = head.insert(index, item);
    }

    @Override
    public void remove(int index) throws InvalidIndex {
        head = head.remove(index);
    }

    @Override
    public int search(T item) {
        return  head.search(item);
    }

}


interface List<T> {

    int length();

    T get (int index) throws InvalidIndex;

    void set(int index, T item) throws InvalidIndex;

    List<T> insert (int index, T item) throws InvalidIndex;

    List<T> remove (int index) throws InvalidIndex;

    int search(T item);

}


class RNode<T> implements List<T> {

    private T item;
    private List<T> next;

    RNode (T item, List<T> next) {
        this.item = item;
        this.next = next;
    }

    @Override
    public T get (int index) throws InvalidIndex {
        if (index < 1) throw new InvalidIndex(index);
        if (index == 1) return item;
        return next.get(index-1);
    }

    @Override
    public void set (int index, T newItem) throws InvalidIndex {
        if (index < 1) throw new InvalidIndex(index);
        if (index == 1) { item = newItem; return; }
        next.set(index-1, newItem);
    }

    @Override
    public int length () {
        return 1 + next.length();
    }

    @Override
    public List<T> insert (int index, T item) throws InvalidIndex {
        if (index < 1) throw new InvalidIndex(index);
        if (index == 1) return new RNode<>(item, this);
        return new RNode<>(this.item, next.insert(index-1, item));
    }

    @Override
    public List<T> remove (int index) throws InvalidIndex {
        if (index < 1) throw new InvalidIndex(index);
        if (index == 1) return next;
        next = next.remove(index-1);
        return this;
    }

    @Override
    public int search (T item) {
        if (this.item.equals(item)) return 1;
        var result = next.search(item);
        return result == 0 ? 0 : 1 + result;
    }

}



class Empty<T> implements List<T> {

    Empty() {}

    @Override
    public T get (int index) throws InvalidIndex {
        throw new InvalidIndex(index);
    }

    @Override
    public void set (int index, T newItem) throws InvalidIndex {
        throw new InvalidIndex(index);
    }

    @Override
    public int length () {
        return 0;
    }

    @Override
    public List<T> insert (int index, T item) throws InvalidIndex {
        if (index < 1 || index > 1) throw new InvalidIndex(index);
        return new RNode<>(item, new Empty<>()); 
    }

    @Override
    public List<T> remove (int index) throws InvalidIndex {
        throw new InvalidIndex(index);
    }

    @Override
    public int search (T item) {
        return 0;
    }

}
