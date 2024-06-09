package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    //sites1 without bottom sites to avoid backwash
    private WeightedQuickUnionUF sites2;
    private WeightedQuickUnionUF sites1;
    private boolean[][] status;
    private int topsite;
    private int bottomSite;
    private int numOfOpen = 0;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        topsite = N * N;
        bottomSite = N * N + 1;
        sites1 = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            sites1.union(xyTo1D(0, i), this.topsite);
        }
        for (int i = 0; i < N; i++) {
            sites1.union(xyTo1D(N - 1, i), bottomSite);
        }

        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites2.union(xyTo1D(0, i), this.topsite);
        }

        status = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                status[i][j] = false;
            }
        }
    }

    public static void main(String[] args) {
    }

    private int xyTo1D(int i, int j) {
        return i * N + j;
    }

    private void unionOpenNeighbor(int row, int col, int newRow, int newCol) {
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            return;
        }
        if (status[newRow][newCol]) {
            sites1.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
            sites2.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }

    public void open(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (isOpen(row, col)) {
            return;
        }
        status[row][col] = true;
        numOfOpen++;
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row, col + 1);
        unionOpenNeighbor(row, col, row, col - 1);
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        return status[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
        if (status[row][col]) {
            return sites2.connected(xyTo1D(row, col), topsite);
        }
        return false;
    }

    public int numberOfOpenSites() {
        return numOfOpen;
    }

    public boolean percolates() {
        if (numOfOpen == 0) {
            return false;
        }
        return sites1.connected(topsite, bottomSite);
    }
}
