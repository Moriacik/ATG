import java.util.ArrayList;
import java.util.List;

public class KruskalAlg {

    private Graf graf;
    private List<int[]> kostra;
    private List<int[]> P;
    private int[] k;

    public KruskalAlg(Graf graf) {
        this.graf = graf;
        this.kostra = new ArrayList<>();
        this.P = new ArrayList<>();
        najdrahsiaKostra();
    }

    public void najdrahsiaKostra() {
        this.k = new int[graf.n + 1];

        for (int i = 1; i <= graf.m; i++) {
            P.add(new int[]{graf.H[i][0], graf.H[i][1], graf.H[i][2]});
        }

        P.sort((a, b) -> Integer.compare(b[2], a[2]));

        for (int i = 1; i <= graf.n; i++) {
            k[i] = i;
        }

        while (kostra.size() < graf.n - 1 && !P.isEmpty()) {
            int u = k[P.getFirst()[0]];
            int v = k[P.getFirst()[1]];

            if (u != v) {
                kostra.add(P.getFirst());
                for (int y = 0; y < graf.n; y++) {
                    if (k[y] == v) {
                        k[y] = u;
                    }
                }
            }
            P.removeFirst();
        }
    }

    public void printInfo() {
        int cost = 0;
        System.out.println("");
        System.out.println("Kostra grafu:");
        for (int[] edge : kostra) {
            cost += edge[2];
            System.out.println(edge[0] + " -> " + edge[1] + " s ohodnotením " + edge[2]);
        }
        System.out.println("Pocet hran kostry: " + kostra.size());
        System.out.println("Cena kostry: " + cost);
    }
}