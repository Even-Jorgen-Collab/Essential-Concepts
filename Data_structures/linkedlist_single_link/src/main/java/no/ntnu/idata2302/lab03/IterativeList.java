/*
 * This file is part of NTNU's IDATA2302 Lab 03.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab03;


public class IterativeList<T> extends Sequence<T> {

    private INode<T> head;

    public IterativeList (INode<T> head) {
        super();
        this.head = head;
    }

    @Override
    public int length() {
        int length = 0;
        INode<T> currentNode = head;
        while (currentNode != null) {
            length++;
            currentNode = currentNode.next;
        }
        return length;
    }

    @Override
    public boolean isEmpty () {
        return length() == 0;
    }

    @Override
    public T get(int index) throws InvalidIndex {
        if (index > length()) {throw new InvalidIndex(index);}
        var node = getNode(index);
        return node.item;
    }

    /**
     * @returns the *node* with the given index
     */
    private INode<T> getNode(int index) throws InvalidIndex {
        if (index < 1) {
            throw new InvalidIndex(index);
        }
        int currentIndex = 1;
        INode<T> currentNode = head;
        while (currentNode != null) {
            if (index == currentIndex) { 
                return currentNode;
            }         
            currentNode = currentNode.next;
            currentIndex++;
        }
        return null;
    }

    @Override
    public void set(int index, T item) throws InvalidIndex {
        var node = getNode(index);
        node.item = item;
    }

    @Override
    public void insert(int index, T item) throws InvalidIndex {
        if (index < 1) throw new InvalidIndex(index);
        if (index == 1) { 
            if (head == null) head = new INode<>(item, null);
            else head = new INode<>(item, head); 
        } else {
            INode<T> prev = getNode(index-1);
            INode<T> curr = getNode(index);
            prev.next = new INode<>(item, curr);
        }
    }


    @Override
    public void remove(int index) throws InvalidIndex {
        if (index == 1) {
            head = head.next;

        } else {
            var previous = getNode(index-1);
            previous.next = previous.next.next;

        }
    }


    @Override
    public int search(T item) {
        int index = 1;
        var currentNode = head;
        while (currentNode != null) {
            if (currentNode.item == item) return index;
            currentNode = currentNode.next;
            index++;
        }
        return 0;
    }

}


class INode<T> {
    T item;
    INode<T> next;

    INode(T item, INode<T> next) {
        this.item = item;
        this.next = next;
    }

}