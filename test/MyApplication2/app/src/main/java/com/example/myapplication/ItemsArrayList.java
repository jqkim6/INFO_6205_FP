package com.example.myapplication;
public class ItemsArrayList <E> {
    private Object[] elements; // 使用Object数组存储元素
    private int size = 0; // 实际存储的元素数量
    public ItemsArrayList(int initialCapacity) {
        if (initialCapacity < 0) {throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);}
        this.elements = new Object[initialCapacity];}
    public ItemsArrayList() {
        this(10);
    }
    public void add(E e) {ensureCapacity(size + 1); // 确保有足够的容量
        elements[size++] = e;}
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {grow(minCapacity);}}
    private void grow(int minCapacity) {int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5倍
        if (newCapacity - minCapacity < 0) {newCapacity = minCapacity;}
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;}
    public E get(int index) {checkIndex(index);
        return (E) elements[index];}
    public E remove(int index) {checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {System.arraycopy(elements, index + 1, elements, index, numMoved);}
        elements[--size] = null; // clear to let GC do its work
        return oldValue;}
    public void swap(int index1, int index2) {checkIndex(index1);
        checkIndex(index2);
        if (index1 != index2) {E temp = (E) elements[index1];
            elements[index1] = elements[index2];
            elements[index2] = temp; }}
    private void checkIndex(int index) {if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);}}
    public int size() {return size;}
    public boolean isEmpty() {return size == 0;}
}
