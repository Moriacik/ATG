import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class FordFulkerson {
    Graf g;
    int[][] capacity;
    int[][] flow;
    int[] parent;
    int maxFlow = 0;
    int destination;
    int source;

    public FordFulkerson(Graf g) {
        this.g = g;
        this.capacity = new int[g.n + 1][g.n + 1];
        this.flow = new int[g.n + 1][g.n + 1];
        this.parent = new int[g.n + 1];

        for (int i = 1; i <= g.m; i++) {
            int u = g.H[i][0];
            int v = g.H[i][1];
            int c = g.H[i][2];
            capacity[u][v] += c;
        }
    }

    private boolean bfs(int source, int destination) {
        int[] x = new int[g.n + 1];
        Arrays.fill(x, Integer.MAX_VALUE);
        x[source] = 0;

        ArrayList<Integer> E = new ArrayList<>();
        E.add(source);

        while (!E.isEmpty()) {
            int r = E.remove(0);

            for (int j = 1; j <= g.n; j++) {
                if (x[j] == Integer.MAX_VALUE && capacity[r][j] - flow[r][j] > 0) {
                    x[j] = r;
                    E.add(j);
                }
                if (x[j] == Integer.MAX_VALUE && flow[j][r] > 0) {
                    x[j] = -r;
                    E.add(j);
                }
            }

            if (x[destination] != Integer.MAX_VALUE) {
                int v = destination;
                while (v != source) {
                    parent[v] = x[v] < 0 ? -x[v] : x[v];
                    v = parent[v];
                }
                return true;
            }
        }
        return false;
    }


    public int fordFulkerson(int source, int destination) {
        this.source = source;
        this.destination = destination;

        while (bfs(source, destination)) {
            int r = Integer.MAX_VALUE;

            for (int v = destination; v != source; v = parent[v]) {
                int u = parent[v];
                r = Math.min(r, capacity[u][v] - flow[u][v]);
            }

            for (int v = destination; v != source; v = parent[v]) {
                int u = parent[v];
                flow[u][v] += r;
                flow[v][u] -= r;
            }

            maxFlow += r;
        }
        return maxFlow;
    }

    public void printInfo() {
        System.out.println("\nTok na jednotlivých hranách:");
        for (int u = 1; u <= g.n; u++) {
            for (int v = 1; v <= g.n; v++) {
                    System.out.println(u + " -> " + v + ": " + flow[u][v]);

            }
        }

        System.out.println("\nMaximalny tok: " + maxFlow);

        ArrayList<Integer> path = new ArrayList<>();
        int v = destination;
        while (v != source) {
            path.add(v);
            v = Math.abs(parent[v]);
        }
        path.add(source);
        Collections.reverse(path);

        System.out.println("\nZväčšujúca sa polocesta:");
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            v = path.get(i + 1);
            int r;
            if (parent[v] > 0) {
                r = capacity[u][v] - flow[u][v];
                System.out.println(u + " -> " + v + " s rezervou " + r);
            } else {
                r = flow[v][u];
                System.out.println(v + " -> " + u + " s rezervou " + r);
            }
        }
    }
}