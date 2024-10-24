package com.example.cs213project3;

import java.util.Iterator;

/**
 * This is a generic class which holds a list of objects
 * @param <E> the list takes a generic type
 * @author Ryan Cepeda
 */

//Try to find way to make it work with implements<Iterator>
@SuppressWarnings("unchecked")
public class List<E> implements Iterable<E> {
    private E[] objects;
    private int size;

    /**
     * Default constructor of the list class with an initial capacity of 4.
     */
    public List(){
        this.size = 0;
        objects = (E[]) new Object[4];
    }

    /**
     * Finds the index of an object in the list
     * @param e the object searched for
     * @return the index of the object in the list, -1 if not found
     */
    private int find(E e){
        for(int i = 0; i<size;i++){
            if(objects[i].equals(e)){
                return i;
            }
        }
        return -1;
    }

    /**
     * This method doubles the size of the list
     */
    private void grow(){
        E[] newArr = (E[])new Object[objects.length + 4];
        for(int i = 0; i < size;i++){
            newArr[i] = objects[i];
        }
        objects = newArr;
    }

    /**
     * Returns whether the same object exists already in the list
     * @param e object to be compared with
     * @return true if found, false otherwise
     */
    public boolean contains(E e){
        return find(e) != -1;
    }

    /**
     * Adds an object to the list
     * @param e the object to be added
     */
    public void add(E e){
        if (contains(e)) {
            return; //if the object already exists, there is no need to add.
        }
        if(size == objects.length){
            grow();
        }
        objects[size] = e;
        size++;
    }

    /**
     * This method removes an object from the list
     * @param e is the object to be removed.
     */
    public void remove(E e){
        int index = find(e);

        if(index==-1) return;
        for(int i = index+1; i<size;i++){
            objects[i-1] = objects[i];
        }
        objects[size-1] = null;
        size--;
    }

    /**
     * Sees if list is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty(){
        return size==0;
    }

    /**
     * Returns the size of the current list.
     * @return an integer of size. 
     */
    public int size() {
        return size;
    }

    /**
     * Returns the object at the index
     * @param index to be searched
     * @return object at the index
     */
    public E get(int index){
        if(index < 0 || index>=size){
            return null;
        }
        return objects[index];
    }

    /**
     * Replaces the element at the specified index with the given element.
     * @param index the index of the element to replace.
     * @param e the element to be stored at the specified index.
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {  // Ensure the index is within bounds
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        objects[index] = e;  // Replace the element at the specified index
    }

    /**
     * return the index of the object
     * @param e object to be searched for
     * @return index of the object or -1
     */
    public int indexOf(E e){
       return find(e);
    }

    /**
     * Returns an iterator for the list
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    /**
     * Private iterator class for iterating through the list
     */
    private class ListIterator implements Iterator<E>{
        private int currentIndex = 0;

        /**
         * @Override the hasNext() method
         * @return true if list has a variable next and False otherwise
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * @Override the next() method
         * @return the next object in the list
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new RuntimeException("No more elements in the list.");
            }
            return objects[currentIndex++];
        }
    }


}