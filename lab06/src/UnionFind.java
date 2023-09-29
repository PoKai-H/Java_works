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
        for (int i = 0; i < data.length; i++) {
            data[i] = -1;
        }

    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        if (v > -1) {
            int index = v;
            while (data[index] > 0) {
                index = data[index];
            }
            return data[index];
        }
        else {
            return v;
        }
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
        if (v < data.length) {
            int par = parent(v);
            int prev = 0;
            int [] path = new int[data.length];
            int i = 0;
            path[i] = v;
            while (par > 0) {
                i += 1;
                path[i] = par;
                prev = par;
                par = parent(par);
            }
            if (i > 0) {
                for (int j : path) {
                    path[j] = prev;
                }
            }
            return prev;
        }
        return 0;
    }


    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 < data.length && v2 < data.length) {
            if (v1 != v2 && !connected(v1, v2)) {
                int size1 = sizeOf(v1);
                int size2 = sizeOf(v2);
                if (size1 < size2) {
                    data[find(v2)] = find(v1);
                    data[find(v1)] = sizeOf(v2) + sizeOf(v1);
                }
                else {
                    data[find(v1)] = find(v2);
                    data[find(v2)] = sizeOf(v1) + sizeOf(v2);
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
