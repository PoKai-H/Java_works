import java.util.Arrays;

public class UnionFind {
    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     * You can assume that we are only working with non-negative integers as the items
     * in our disjoint sets.
     */
    private int[] data;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        data = new int [N];
        Arrays.fill(data, -1);

    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        return Math.abs(data[find(v)]);
    }


    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (data[v] > -1) {
            return data[v];
        }
        else {
            return sizeOf(v);
        }
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return data[v1] == v2 || data[v2] == v1;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v > data.length) {
            throw new IllegalArgumentException("Index out of bound");
        }
        if (data[v] < 0) {
            return v;
        }
        else {
            data[v] = find(data[v]);
            return data[v];
        }
    }


    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 < data.length && v2 < data.length) {
            if (v1 != v2 && !connected(v1, v2)) {
                int root1 = find(v1);
                int root2 = find(v2);
                if (sizeOf(v1) > sizeOf(v2)) {
                    data[root1] += data[root2];
                    data[root2] = root1;
                }
                else {
                    data[root2] += data[root1];
                    data[root1] = root2;
                }
            }
        }
        else {
            throw new IllegalArgumentException("Index out of bound");
        }

    }

    /**
     * DO NOT DELETE OR MODIFY THIS, OTHERWISE THE TESTS WILL NOT PASS.
     */
    public int[] returnData() {
        return data;
    }
}
