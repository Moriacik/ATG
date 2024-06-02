import java.util.*;

public class LabelSet {
    Graf graf; // Graf, na ktorom budeme vykonávať algoritmus
    int[] vrcholy, predchodcovia, dlzkaNajkratsejCesty; // Polia pre označenia vrcholov, ich predchodcov a dĺžky najkratších ciest
    List<Integer> queue = new ArrayList<>(); // Zoznam vrcholov, ktoré čakajú na vyhodnotenie

    // Konštruktor triedy, inicializuje graf a polia
    public LabelSet(Graf graf) {
        this.graf = graf;
        int n = graf.n;
        vrcholy = new int[n + 1];
        predchodcovia = new int[n + 1];
        dlzkaNajkratsejCesty = new int[n + 1];

        // Inicializácia polí na počiatočné hodnoty
        for (int i = 0; i <= n; i++) {
            vrcholy[i] = Integer.MAX_VALUE;
            predchodcovia[i] = -1;
            dlzkaNajkratsejCesty[i] = Integer.MAX_VALUE;
        }
    }

    // Metóda na vykonanie algoritmu Label-set pre hľadanie najkratšej cesty
    private void najkratsiaCesta(int zaciatok, int ciel) {
        vrcholy[zaciatok] = 0; // Začiatočný vrchol má značku 0
        dlzkaNajkratsejCesty[zaciatok] = 0; // Dĺžka najkratšej cesty z počiatočného vrcholu je 0
        queue.add(zaciatok); // Pridáme začiatočný vrchol do fronty

        // Cyklus vykonáva algoritmus, kým nie je fronta prázdna
        while (!queue.isEmpty()) {
            // Vyberieme vrchol s najnižšou značkou
            int minIndex = 0;
            for (int i = 1; i < queue.size(); i++) {
                if (vrcholy[queue.get(i)] < vrcholy[queue.get(minIndex)])
                    minIndex = i;
            }
            int aktualny = queue.remove(minIndex); // Odoberáme prvok s nejnižším označením

            // Ak sme dosiahli cieľový vrchol, končíme
            if (aktualny == ciel)
                break;

            // Prejdeme všetkých susedov aktuálneho vrchola
            for (int i = 1; i <= graf.m; i++) {
                int[] h = graf.H[i];
                if (h[0] == aktualny) {
                    int sused = h[1], vahaHrany = h[2];

                    // Aktualizujeme označenie, dĺžku najkratšej cesty a predchodcu susedného vrchola
                    if (vrcholy[sused] > vrcholy[aktualny] + vahaHrany) {
                        vrcholy[sused] = vrcholy[aktualny] + vahaHrany;
                        dlzkaNajkratsejCesty[sused] = dlzkaNajkratsejCesty[aktualny] + vahaHrany; // Aktualizujeme dĺžku najkratšej cesty
                        predchodcovia[sused] = aktualny;
                        queue.add(sused);
                    }
                }
            }
        }
    }

    // Metóda na získanie najkratšej cesty z rekonštrukcie predchodcov
    private List<Integer> najkratsiaCestaRekonstrukcia(int zaciatok, int ciel) {
        najkratsiaCesta(zaciatok, ciel);
        ArrayList<Integer> cesta = new ArrayList<>();
        int vrchol = ciel;
        while (vrchol != -1) {
            cesta.add(vrchol);
            vrchol = predchodcovia[vrchol];
        }
        Collections.reverse(cesta);
        return cesta;
    }

    private int dlzkaNajkratsejCesty(int ciel) {
        return dlzkaNajkratsejCesty[ciel];
    }

    // Výpis pre pre Label-set algoritmus
    public void printInfo(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Zaciatocny vrchol:");
        int zaciatok = sc.nextInt();
        System.out.println("Cielovy vrchol:");
        int ciel = sc.nextInt();

        System.out.println("Najkratšia cesta z vrcholu " + zaciatok + " do vrcholu " + ciel + ":");
        System.out.println(najkratsiaCestaRekonstrukcia(zaciatok, ciel));
        System.out.println("Dlzka cesty je: " + dlzkaNajkratsejCesty(ciel));
    }
}