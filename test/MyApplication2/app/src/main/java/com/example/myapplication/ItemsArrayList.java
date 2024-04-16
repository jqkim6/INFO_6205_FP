package com.example.myapplication;

public class ItemsArrayList <E> {
    private Object[] elements; // 使用Object数组存储元素
    private int size = 0; // 实际存储的元素数量

    // 构造函数，初始化数组大小
    public ItemsArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
    }

    // 默认构造函数，初始容量为10
    public ItemsArrayList() {
        this(10);
    }

    // 添加元素到列表尾部
    public void add(E e) {
        ensureCapacity(size + 1); // 确保有足够的容量
        elements[size++] = e;
    }

    // 确保数组有足够的空间，如果没有则扩容
    private void ensureCapacity(int minCapacity) {
        if (minCapacity - elements.length > 0) {
            grow(minCapacity);
        }
    }

    // 数组扩容，通常扩展到原来的1.5倍
    private void grow(int minCapacity) {
        int oldCapacity = elements.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1); // 1.5倍
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        Object[] newElements = new Object[newCapacity];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    // 根据索引获取元素
    public E get(int index) {
        checkIndex(index);
        return (E) elements[index];
    }

    // 根据索引移除元素
    public E remove(int index) {
        checkIndex(index);
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
        return oldValue;
    }

    // 交换数组中两个指定索引位置的元素
    public void swap(int index1, int index2) {
        checkIndex(index1); // 检查第一个索引是否有效
        checkIndex(index2); // 检查第二个索引是否有效

        if (index1 != index2) { // 仅当两个索引不同才需要交换
            E temp = (E) elements[index1]; // 保存第一个位置的元素
            elements[index1] = elements[index2]; // 将第二个位置的元素移到第一个位置
            elements[index2] = temp; // 将保存的元素移到第二个位置
        }
    }

    // 检查索引是否有效
    private void checkIndex(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    // 获取列表大小
    public int size() {
        return size;
    }

    // 判断列表是否为空
    public boolean isEmpty() {
        return size == 0;
    }
}
