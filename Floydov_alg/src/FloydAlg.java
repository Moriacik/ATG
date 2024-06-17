import java.util.Arrays;

public class FloydAlg {

    final static int INF = Integer.MAX_VALUE;
    private int[][] dist;
    private int[][] prev;

    public FloydAlg(Graf g) {
        int n = g.n;

        dist = new int[n][n];
        prev = new int[n][n];
        final int INF = Integer.MAX_VALUE;

        for (int[] row : dist) {
            Arrays.fill(row, INF);
        }
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
            prev[i][i] = i;
        }
        for (int j = 1; j <= g.m; j++) {
            int u = g.H[j][0] - 1;
            int v = g.H[j][1] - 1;
            int c = g.H[j][2];
            dist[u][v] = c;
            prev[u][v] = u;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        prev[i][j] = prev[k][j];
                    }
                }
            }
        }

        vypisMaticu("Matica vzdialeností", dist);
        vypisMaticu("Matica predchodcov", prev);
    }

    public static void vypisMaticu(String label, int[][] matica) {
        System.out.println(label + ":");
        for (int[] riadok : matica) {
            for (int hodnota : riadok) {
                if (hodnota == INF) {
                    System.out.print("INF ");
                } else {
                    if (label.equals("Matica vzdialeností")) {
                        System.out.print((hodnota) + " ");
                    } else {
                        System.out.print((hodnota + 1) + " ");
                    }
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void vytlacCestuRekurzivne(int u, int v) {
        if (u == v) return;
        vytlacCestuRekurzivne(u, prev[u][v]);
        System.out.print((prev[u][v] + 1) + " -> ");
    }

    public void vytlacCestu(int u, int v) {
        if (dist[u][v] == INF) {
            System.out.println("Žiadna cesta neexistuje");
            return;
        }
        System.out.print("Najkratšia cesta z " + (u + 1) + " do " + (v + 1) + ": ");
        vytlacCestuRekurzivne(u, v);
        System.out.println((v + 1));
        System.out.println("Dĺžka cesty: " + dist[u][v]);
    }
}