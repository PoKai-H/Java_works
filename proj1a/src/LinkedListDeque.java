import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    private StuffNode sentinel;
    private int size;

    private class StuffNode {
        private T item;
        private StuffNode next;
        private StuffNode prev;
        public StuffNode(T i) {
            item = i;
            prev = null;
            next = null;
        }
    }
    public LinkedListDeque() {
        sentinel = new StuffNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;

    }
    @Override
    public void addFirst(T x) {
        StuffNode newNode = new StuffNode(x);
        newNode.prev = sentinel;
        newNode.next = sentinel.next;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;

    }

    @Override
    public void addLast(T x) {
        StuffNode newNode = new StuffNode(x);
        newNode.next = sentinel.next;
        newNode.prev = sentinel.prev;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
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
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return res;
    }


    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return res;
    }

    @Override
    public T get(int index) {
        int count = 0;
        StuffNode curr = sentinel;
        while(curr.next != sentinel) {
            curr = curr.next;
            if(count == index) {
                return curr.item;
            }
            count ++;
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
