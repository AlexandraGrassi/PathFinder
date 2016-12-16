package net.ucoz.talg.utils;

public class MyDynamicArray<T> {
    private Object[] array;
    private int realSize;

    public MyDynamicArray() {
        this.array = new Object[0];
        realSize = 0;
    }

    public void add(T e) {
        if (realSize + 1 < array.length) {
            array[realSize] = e;
        } else {
            Object[] currentArray = new Object[(array.length * 3) / 2 + 1];
            System.arraycopy(array, 0, currentArray, 0, array.length);
            currentArray[realSize] = e;
            this.array = currentArray;
        }

        realSize++;
    }

    public boolean addToIndex(T e, int index) {
        if (index > array.length)
            return false;
        Object[] tmpArray = new Object[array.length + 1];
        for (int i = 0; i < index; i++) {
            tmpArray[i] = array[i];
        }
        for (int i = index; i < array.length; i++) {
            tmpArray[i + 1] = array[i];
        }
        tmpArray[index] = e;
        array = tmpArray;
        return true;
    }

    public boolean remove(T e) {
        if (realSize == 0) return false;

        Object[] currentArray = this.array;
        if (realSize - 1 < array.length / 2) {
            currentArray = new Object[array.length / 2];
        }

        boolean found = false;
        int position = 0;
        for (int i = 0; i < realSize; i++) {
            if (!found && array[i].equals(e)) {
                found = true;
            } else {
                currentArray[position] = array[i];
                position++;
            }
        }
        realSize--;
        this.array = currentArray;

        return found;
    }

    public T get(int index) {
        if (index < 0 || index >= realSize) {
            return null;
        }

        return (T) array[index];
    }

    public boolean available(T e) {
        for (int i = 0; i < realSize; i++) {
            if (array[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        array = new Object[0];
        realSize = 0;
    }

    public int getSize() {
        return realSize;
    }

    public int getCapacity() {
        return array.length;
    }

    public boolean isEmpty() {
        return realSize == 0;
    }
}