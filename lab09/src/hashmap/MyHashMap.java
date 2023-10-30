package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private  final int INITIAL_CAPACITY = 16;
    private  double LOAD_FACTOR = 0.75;
    // You should probably define some more!
    private int size;

    /** Constructors */

    private void initialize(int capacity) {
        for (int i = 0; i < capacity; i++){
            this.buckets[i] = createBucket();
        }
    }
    public MyHashMap() {
        this.buckets = (Collection<Node>[]) new Collection[INITIAL_CAPACITY];
        size = 0;
        initialize(INITIAL_CAPACITY);
    }

    public MyHashMap(int initialCapacity) {
        this.buckets = (Collection<Node>[]) new Collection[initialCapacity];
        size = 0;
        initialize(initialCapacity);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */

    public MyHashMap(int initialCapacity, double loadFactor) {
        this.buckets = (Collection<Node>[]) new Collection[initialCapacity];
        size = 0;
        this.LOAD_FACTOR = loadFactor;
        initialize(initialCapacity);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    // Your code won't compile until you do so!
    public double threshold() {
        return (double)size/ buckets.length;
    }

    private int hash(K key) {
        return Math.floorMod(key.hashCode(), this.buckets.length);
    }
        // write a helper method
    private Collection<Node> bucket(K key) {
        int index = hash(key);
        return buckets[index];
    }
    @Override
    public void put(K key, V value) {
        // hash(key) -> index corresponding to a bucket to place it in
        // get the bucket by indexing into the buckets array
        // if bucket contains key given, just update the value
        // else, insert a new node containing the key-value pair to the bucket's Collection
        // keep track of size and also figure out when to resize
        Collection<Node> bucket = bucket(key);
        boolean found = false;
        for (Node n : bucket) {
            if (n.key.equals(key)) {
                n.value = value;
                found = true;
                break;
            }
        }
        if (!found) {
            bucket.add(new Node(key, value));
            size++;
        }

        if (threshold() > LOAD_FACTOR) {
            resize();
        }

    }
    @Override
    public V get(K Key) {
        // hash(key) -> index corresponding to a bucket to place it in
        // get the bucket by indexing into the buckets array
        // if bucket contains key given, return the value
        // else, return null
        Collection<Node> bucket = bucket(Key);
        for(Node n: bucket) {
            if (n.key.equals(Key)) {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        // hash(key) -> index corresponding to a bucket to place it in
        // get the bucket by indexing into the buckets array
        // if bucket contains key given, return true
        // else, return false
        Collection<Node> bucket = bucket(key);
        for (Node n: bucket) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        // reset buckets to initial empty state
        // reset any corresponding variables
        this.size = 0;
        this.buckets = new Collection[INITIAL_CAPACITY];
        initialize(INITIAL_CAPACITY);
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    private void resize() {
        int newCapacity = buckets.length * 2;
        Collection<Node>[] temp = this.buckets;
        this.buckets = (Collection<Node>[]) new Collection[newCapacity];
        initialize(newCapacity);
        this.size = 0;
        for (Collection<Node> bucket : temp) {
            for (Node n: bucket) {
                put(n.key, n.value);
            }
        }
    }

}
