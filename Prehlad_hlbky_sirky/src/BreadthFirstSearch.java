import java.util.*;

public class BreadthFirstSearch {
    Graf graf;
    List<Integer> T = new ArrayList<>();

    public BreadthFirstSearch(Graf graf) {
        this.graf = graf;
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
            int current = queue.removeFirst();

            for (int i = 1; i <= graf.m; i++) {
                int u = graf.H[i][0];
                int v = graf.H[i][1];

                if ((u == current || v == current) && ((!inTree[u] && p[u] == 0) || (!inTree[v] && p[v] == 0))) {
                    int next = (u == current) ? v : u;
                    T.add(current);
                    T.add(next);
                    k++;
                    p[next] = k;
                    inTree[next] = true;
                    queue.add(next);
                }
            }
        }
    }

    public void printInfo() {
        System.out.println("\nMinimálny strom (prehladavanie do šírky):");
        for (int i = 0; i < T.size(); i += 2) {
            System.out.print("{" + T.get(i) + "," + T.get(i + 1) + "}, ");
        }
    }
}