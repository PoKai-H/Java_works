import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        T item;
        StuffNode next;
        StuffNode prev;
        StuffNode(StuffNode prev, T i, StuffNode next) {
            this.item = i;
            this.prev = prev;
            this.next = next;
        }
    }
    public LinkedListDeque() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }
    @Override
    public void addFirst(T x) {
        sentinel.next = new StuffNode(sentinel, x, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;

    }

    @Override
    public void addLast(T x) {
        sentinel.prev = new StuffNode(sentinel.prev, x, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        StuffNode curr = sentinel;
        int temp = size;
        if (temp == 0) {
            return returnList;
        }
        while (temp > 0) {
            returnList.add(curr.next.item);
            curr = curr.next;
            temp -= 1;
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

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }
        StuffNode temp = sentinel.next;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;
        return temp.item;
    }


    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        StuffNode temp = sentinel.prev;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;
        return temp.item;
    }

    @Override
    public T get(int index) {
        int count = 0;
        StuffNode curr = sentinel;
        while (curr.next != sentinel) {
            curr = curr.next;
            if (count == index) {
                return curr.item;
            }
            count++;
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, StuffNode curr) {
        if (index == 0) {
            return curr.item;
        }
        return getRecursiveHelper(index - 1, curr.next);
    }
}
