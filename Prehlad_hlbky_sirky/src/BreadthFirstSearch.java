import java.util.*;

public class BreadthFirstSearch {
    Graf graf;
    int[][] T;

    public BreadthFirstSearch(Graf graf) {
        this.graf = graf;
        T = new int[graf.n - 1][2];
    }

    public void minimalSpanningTree(int source) {
        boolean[] inTree = new boolean[graf.n + 1];
        ArrayList<Integer> queue = new ArrayList<>();

        int[] p = new int[graf.n + 1];
        p[source] = 1;
        int k = 1;

        queue.add(source);
        inTree[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.getFirst();
            boolean found = false;

            for (int i = 1; i <= graf.m; i++) {
                int u = graf.H[i][0];
                int v = graf.H[i][1];
                int minP = Integer.MAX_VALUE;

                for (int y = 1; y <= graf.n; y++) {
                    if (inTree[y] && p[y] < minP && p[y] != 0) {
                        Math.min(minP,p[y]);
                    }
                }

                if (u == current && !inTree[v] && p[u] <= minP) {
                    T[k - 1][0] = u;
                    T[k - 1][1] = v;

                    k++;
                    p[v] = k;
                    inTree[v] = true;
                    queue.add(v);
                    found = true;
                    break;
                } else if (v == current && !inTree[u] && p[v] <= minP) {
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
                queue.removeFirst();
            }
        }
    }

    public void printInfo() {
        System.out.println("\nMinimálny strom (prehladavanie do šírky):");
        for (int i = 0; i < T.length; i++) {
            if (T[i][0] != 0 && T[i][1] != 0) {
                System.out.print("{" + T[i][0] + "," + T[i][1] + "}, ");
            }
        }
    }
}