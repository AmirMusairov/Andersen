package Collections.ArrayList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArray<T> implements Iterable<T>, MyArrayList<T> {

    private T[] elements;
    private int index;
    private int size;
    private static final int CAPACITY = 10;

    public MyArray() {
        elements = (T[]) new Object[CAPACITY];
    }

    @Override
    public void add(T value) {
        if (index == elements.length) {
            growArray();
        }
        elements[index] = value;
        index++;
        size++;
    }

    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        return (T) elements[index];
    }

    @Override
    public boolean set(int index, T value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        if (index < size) {
            T oldValue = elements(index);
            elements[index] = value;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(int index, T value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
        return true;
    }

    @Override
    public int indexOf(T value) {
        if (value == null) {
            for (int i = 0; i < size; i++) {
                if (elements[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (value.equals(elements[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        }
        T oldValue = elements[index];
        int value = size - index - 1;
        if (value > 0) {
            System.arraycopy(elements, index + 1, elements, index, value);
        }
        elements[--size] = null;
        return oldValue;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int cursor;

            @Override
            public boolean hasNext() {
                if (size >= elements.length) {
                    return false;
                }
                return true;
            }

            @Override
            public T next() {
                int i = cursor;
                if (i >= size) {
                    throw new NoSuchElementException();
                }
                T[] elements2 = elements;
                if (i > elements2.length) {
                    throw new ConcurrentModificationException();
                }
                cursor = i + 1;
                return (T) elements2[i];
            }
        };
    }

    private void growArray() {
        T[] newArray = (T[]) new Integer[elements.length * 2];
        System.arraycopy(elements, 0, newArray, 0, index - 1);
        elements = newArray;
    }

    private T elements(int index) {
        return (T) elements[index];
    }

    public static void main(String[] args) {

        MyArray<Integer> list = new MyArray<Integer>();
        System.out.println("size: " + list.size());
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        System.out.println("size after add: " + list.size());
        list.remove(15);
        list.remove(66);
        list.remove(97);
        System.out.println("size after remove: " + list.size());
        System.out.println("indexOf: " + list.indexOf(88));
        System.out.println("get: " + list.get(33));
        System.out.println("set: " + list.set(95, 8));
        System.out.println("add: " + list.add(11, 1996));
        System.out.println("final size: " + list.size());

    }
}
