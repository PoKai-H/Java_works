import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    private boolean[][] grid;
    private int openSites;
    private WeightedQuickUnionUF UF;
    private WeightedQuickUnionUF UFwithoutBackWash;
    private int N;
    private  int virtualTop ;
    private  int virtualBottom ;

    public Percolation(int N) {
        if (N <= 0) {
            throw new java.lang.IllegalArgumentException("The input must larger than 0");
        }
        this.grid = new boolean[N][N];
        this.openSites = 0;
        this.N = N;
        UF = new WeightedQuickUnionUF(N*N+2);  // n*n grid with virtual top site and virtual bottom site
        UFwithoutBackWash = new WeightedQuickUnionUF(N*N+2);
        this.virtualTop = N*N;
        this.virtualBottom = N*N+1;

    }
    private int xyTo1D(int r, int c) {
        return N*r+c;
    }

    public void open(int row, int col) {
        validateIndex(row, col);
        if (isOpen(row, col)) {return;}
        int [][] next = new int [][] {
                {0, 1},
                {1, 0},
                {0, -1},
                {-1, 0}
        };
        this.grid[row][col] = true;
        this.openSites += 1;
        for (int i = 0; i < 4; i++) {
            int mx = row + next[i][0];
            int my = col + next[i][1];
            if (my < 0 || my > N) {
                continue;
            }
            if (mx == -1) {
                UF.union(xyTo1D(row, col), virtualTop);
                UFwithoutBackWash.union(xyTo1D(row,col), virtualTop);
                continue;
            }
            else if (mx == N) {
                UF.union(xyTo1D(row, col), virtualBottom);
                continue;
            }
            if (isOpen(mx, my) && !UF.connected(xyTo1D(row, col), xyTo1D(mx, my))){
                UF.union(xyTo1D(row, col), xyTo1D(mx, my));
                UFwithoutBackWash.union(xyTo1D(row, col), xyTo1D(mx, my));
            }
        }
    }

    public boolean isOpen(int row, int col) {
        validateIndex(row, col);
        return grid[row][col];
    }

    public boolean isFull(int row, int col) {
        validateIndex(row, col);
        return UFwithoutBackWash.connected(xyTo1D(row, col), N*N);
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    public boolean percolates() {
        return UF.connected(N*N, N*N+1);
    }

    private void validateIndex(int row, int col) {
        if (row < 0 || row > N || col < 0 || col > N) {
            throw new java.lang.IllegalArgumentException("row and col need to be > 0 and < N-1");
        }
    }
    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.

}
