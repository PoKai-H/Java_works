
import java.util.Iterator;
import java.util.Set;

public class BSTMap <K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    public BSTMap() {
        root = null;
    }
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            size = 1;
        }
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private Node insert(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            n.right = insert(n.right, key, value);
        } else if (cmp == 0) {
            n.value = value;
        } else {
            n.left = insert(n.left, key, value);
        }
        n.size = size(n.left) + size(n.right) + 1;
        return n;
    }


    @Override
    public V get(K key) {
        return get(root, key);
    }

    public V get(Node n, K key) {
        if (n == null) {
            return null;
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            return get(n.right, key);
        } else if (cmp < 0){
            return get(n.left, key);
        } else {
            return n.value;
        }
    }

    @Override
    public boolean containsKey(K key) {
        return containsKey(root, key);
    }

    public boolean containsKey(Node n, K key) {
        if (n == null) {
            return false;
        }
        int cmp = key.compareTo(n.key);
        if (cmp > 0) {
            return containsKey(n.right, key);
        } else if (cmp < 0) {
            return containsKey(n.left, key);
        } else {
            return true;
        }

    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    @Override
    public void clear() {
        root = null;
    }

    public void printInOrder(){
        if(root==null){
            System.out.println(" None ");
        }
        else printInOrder(root);
    }

    private void printInOrder(Node n){
        if(n.right==null&&n.left==null){
            System.out.println(" "+n.key.toString()+" ");
        }
        else if(n.right==null&&n.left!=null){
            printInOrder(n.left);
            System.out.println(" " + n.key.toString()+" ");
        }
        else if(n.right!=null&&n.left==null){
            printInOrder(n.right);
            System.out.println(" " + n.key.toString()+" ");
        }
        else{
            printInOrder(n.left);
            System.out.println(" " + n.key.toString()+" ");
            printInOrder(n.right);
        }
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
//        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
//        return null;
    }
}
