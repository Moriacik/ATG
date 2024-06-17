import java.util.*;

public class DepthFirstSearch {
    Graf graf;
    int[][] T;
    int[] p;

    public DepthFirstSearch(Graf graf) {
        this.graf = graf;
        T = new int[graf.n - 1][2];
        p= new int[graf.n + 1];
    }

    public void maximalSpanningTree(int source) {
        boolean[] inTree = new boolean[graf.n + 1];
        ArrayList<Integer> queue = new ArrayList<>();

        p[source] = 1;
        int k = 1;

        queue.add(source);
        inTree[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.getLast();
            boolean found = false;

            for (int i = 1; i <= graf.m; i++) {
                int u = graf.H[i][0];
                int v = graf.H[i][1];
                int maxP = Integer.MIN_VALUE;

                for (int y = 1; y <= graf.n; y++) {
                    if (inTree[y] && p[y] > maxP) {
                        Math.min(maxP,p[y]);
                    }
                }

                if (u == current && !inTree[v] && p[u] >= maxP) {
                    T[k - 1][0] = u;
                    T[k - 1][1] = v;

                    k++;
                    p[v] = k;
                    inTree[v] = true;
                    queue.add(v);
                    found = true;
                    break;
                } else if (v == current && !inTree[u] && p[v] >= maxP) {
                    T[k - 1][0] = v;
                    T[k - 1][1] = u;

                    k++;
                    p[u] = k;
                    inTree[u] = true;
                    queue.add(u);
                    found = true;
                    break;
                }
            }

            if (!found) {
                queue.removeLast();
            }
        }
    }

    public void printInfo() {
        System.out.println("\nMaximalny strom (prehladavanie do hlbky):");
        for (int i = 0; i < T.length; i++) {
            if (T[i][0] != 0 && T[i][1] != 0) {
                System.out.print("{" + T[i][0] + "," + T[i][1] + "}, ");
            }
        }

        for (int i = 0; i < graf.n; i++) {
            System.out.println(p[i]);
        }
    }
}