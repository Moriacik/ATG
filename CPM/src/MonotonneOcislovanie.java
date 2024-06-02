public class MonotonneOcislovanie {
    private int[] ocislovanie;
    private Graf graf;


    public MonotonneOcislovanie(Graf graf) {
        this.graf = graf;
        monotonneOcislovanie();
    }


    public int[] getOcislovanie() {
        return ocislovanie;
    }


    public void monotonneOcislovanie() {
        int[] P = new int[graf.n + 1];
        int[] d = new int[graf.n + 1];
        int k = 0;


        // Krok 1
        for (int v = 0; v <= graf.n; v++) {
            int ideg = 0;
            for (int i = 0; i <= graf.m; i++) {
                if (graf.H[i][1] == v) {
                    ideg++;
                }
            }
            d[v] = ideg;
            if (ideg == 0) {
                P[k] = v;
                k++;
            }
        }


        // Krok 2 a 3
        int i = 0;
        while (k <= graf.n) {
            int vi = P[i];
            for (int j = 0; j <= graf.m; j++) {
                if (graf.H[j][0] == vi) {
                    int w = graf.H[j][1];
                    d[w]--;
                    if (d[w] == 0) {
                        P[k] = w;
                        k++;
                    }
                }
            }
            i++;
        }

        this.ocislovanie = P;
    }


    public void printInfo() {
        System.out.println("\nMonotónne očíslovanie grafu:");
        for (int j = 0; j < graf.n; j++) {
            System.out.println("Vrchol " + (j+1) + ": " + this.ocislovanie[j]);
        }
    }
}