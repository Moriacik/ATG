import java.util.*;

public class DepthFirstSearch {
    Graf graf;
    int[][] T;

    public DepthFirstSearch(Graf graf) {
        this.graf = graf;
        T = new int[graf.n - 1][2];
    }

    public void maximalSpanningTree(int source) {
        boolean[] inTree = new boolean[graf.n + 1];
        ArrayList<Integer> queue = new ArrayList<>();

        int[] p = new int[graf.n + 1];
        p[source] = 1;
        int k = 1;

        queue.add(source);
        inTree[source] = true;

        while (!queue.isEmpty()) {
            int current = queue.get(queue.size() - 1);
            boolean found = false;

            for (int i = 1; i <= graf.m; i++) {
                int u = graf.H[i][0];
                int v = graf.H[i][1];

                if (u == current && !inTree[v]) {
                    T[k - 1][0] = u;
                    T[k - 1][1] = v;

                    k++;
                    p[v] = k;
                    inTree[v] = true;
                    queue.add(v);
                    found = true;
                    break;
                } else if (v == current && !inTree[u]) {
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
                queue.remove(queue.size() - 1);
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
    }
}