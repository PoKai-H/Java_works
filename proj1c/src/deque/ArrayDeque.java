package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int startSize = 8;

    public ArrayDeque() {
        items = (T[]) new Object[startSize];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resizeUp(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < items.length; i++) {
            newArray[i] = items[(nextFirst + 1) % items.length];
            nextFirst = (nextFirst + 1) % items.length;
        }
        items = newArray;
        nextLast = size;
        nextFirst = capacity - 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resizeUp(size * 2);
        }
        size++;
        items[nextFirst] = x;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }

    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resizeUp(size * 2);
        }
        size++;
        items[nextLast] = x;
        nextLast += 1;
        if (nextLast > items.length - 1) {
            nextLast = 0;
        }

    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (isEmpty()) {
            return returnList;
        }
        int temp = nextFirst;
        for (int i = 1; i <= items.length; i++) {
            if (items[(temp + i) % items.length] != null) {
                returnList.add(items[(temp + i) % items.length]);
            }
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void resizeDown(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        int firstPos = Math.abs(capacity - size) / 2;
        for (int i = 0; i < size; i++) {
            newArray[firstPos + i] = items[(nextFirst + i + 1) % items.length];
        }
        items = newArray;
        nextFirst = firstPos - 1;
        nextLast = firstPos + size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (size - 1 <= items.length / 4 && items.length > startSize * 2 - 1) {
            resizeDown(items.length / 2);
        }
        nextFirst = (nextFirst + 1) % items.length;
        T item = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (size - 1 < items.length / 4 && items.length > startSize * 2 - 1) {
            resizeDown(items.length / 2);
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast = (nextLast - 1);
        }
        T item = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index > items.length - 1 || index < 0) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int wizPos = 0;
            int current = nextFirst + 1;

            @Override
            public boolean hasNext() {
                return wizPos < size;
            }

            @Override
            public T next() {
                if (hasNext()) {
                    T data = items[current % items.length];
                    current += 1;
                    wizPos += 1;
                    return data;
                }
                return null;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o instanceof Deque otherDeque) {
            if (otherDeque.size() != size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(otherDeque.get(i).equals(get(i)))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}

