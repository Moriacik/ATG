import java.util.ArrayList;

public class MonotonOcisl {

    Graf graf;
    ArrayList<Integer> P; // Postupnosť pre ukladanie monotónne očíslovaných vrcholov

    public MonotonOcisl(Graf paGraf) {
        graf = paGraf;
        P = new ArrayList<>();
        monotonnaOcislovac();
    }

    private void monotonnaOcislovac() {
        int[] d = new int[graf.n + 1]; // Pole pre uchovávanie ideg

        // Krok 1: Výpočet ideg
        for (int j = 1; j <= graf.m; j++) {
            int v = graf.H[j][1];
            d[v]++;
        }

        ArrayList<Integer> V0 = new ArrayList<>(); // Množina V0 pre ukladanie vrcholov s ideg 0

        // Krok 2: Výber vrcholov s ideg 0 a ich zaradenie do postupnosti P
        for (int v = 1; v <= graf.n; v++) {
            if (d[v] == 0) {
                V0.add(v);
                P.add(v);
            }
        }

        int i = 0;
        // Krok 3: Spracovanie všetkých vrcholov v postupnosti P
        while (i < P.size()) {
            int vi = P.get(i); // Aktuálny vrchol v postupnosti P
            for (int j = 1; j <= graf.m; j++) {
                if (graf.H[j][0] == vi) { // Ak je hrana vychádzajúca z aktuálneho vrchola
                    int w = graf.H[j][1]; // Cieľový vrchol hrany
                    d[w]--; // Zníženie ideg cieľového vrchola
                    if (d[w] == 0) {
                        P.add(w); // Ak je ideg cieľového vrchola 0, pridáme ho do postupnosti P
                    }
                }
            }
            i++;
        }
    }

    public void printInfo() {
        System.out.println("Monotónne očíslovanie:");
        for (int v : P) {
            System.out.print(v + " ");
        }
    }
}