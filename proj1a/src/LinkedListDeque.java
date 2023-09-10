import org.objectweb.asm.tree.IntInsnNode;

import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque<T> implements Deque<T>{
    private StuffNode sentinel;
    private int size;

    private class StuffNode{
        public T item;
        public StuffNode next;
        public StuffNode prev;
        public StuffNode(T i) {
            item = i;
            prev = null;
            next = null;
        }
    }
    public LinkedListDeque(){
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
        size+=1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        StuffNode curr = sentinel;
        int temp = size;
        while(temp > 0){
            returnList.add(curr.next.item);
            curr = curr.next;
            temp -= 1;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next.item == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size --;
        return null;
    }

    @Override
    public T removeLast() {
        if (size==0) {
            return null;
        }
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return null;
    }

    @Override
    public T get(int index) {
        int i =0;
        StuffNode curr = sentinel.next;
        if(index > size || index < 0){
            return null;
        }
        else{
            while (i < index){
                curr = curr.next;
                i += 1;
            }
            return curr.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size){
            return null;
        }
        return getRecursiveHelper(index, sentinel.next);
    }

    private T getRecursiveHelper(int index, StuffNode curr){
        if(index == 0){
            return curr.item;
        }
        return getRecursiveHelper(index-1, curr.next);
    }
}
