import java.util.*;

public class DepthFirstSearch {
    Graf graf;
    int[][] T;

    public DepthFirstSearch(Graf graf) {
        this.graf = graf;
    }

    public void maximalSpanningTree(int source) {
        boolean[] inTree = new boolean[graf.n + 1];
        ArrayList<Integer> stack = new ArrayList<>();

        int[] p = new int[graf.n + 1];
        p[source] = 1;
        int k = 1;

        stack.add(source);
        inTree[source] = true;

        while (!stack.isEmpty()) {
            int current = stack.get(stack.size() - 1);
            boolean found = false;

            for (int i = 1; i <= graf.m; i++) {
                int u = graf.H[i][0];
                int v = graf.H[i][1];

                if (u == current && !inTree[v]) {
                    if (T == null) {
                        T = new int[graf.n - 1][2];
                    }
                    T[k - 1][0] = u;
                    T[k - 1][1] = v;

                    k++;
                    p[v] = k;
                    inTree[v] = true;
                    stack.add(v);
                    found = true;
                    break;
                }
            }

            if (!found) {
                stack.removeLast();
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