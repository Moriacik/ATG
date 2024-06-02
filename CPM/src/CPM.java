import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CPM {
    Graf graf;
    int[] najskorsiMoznyZaciatok;
    int[] najneskorsiNutnyKoniec;
    ArrayList<Integer> krytickaCesta = new ArrayList<>();
    int T = 0;

    MonotonneOcislovanie monotonneOcislovanie;
    private int[] dobaTrvania;

    public CPM(Graf graf) {
        this.graf = graf;
        monotonneOcislovanie = new MonotonneOcislovanie(graf);
        najskorsiMoznyZaciatok = new int[graf.n + 1];
        najneskorsiNutnyKoniec = new int[graf.n + 1];

        //nacitanieCenyCesty("test2" + ".tim");
        nacitanieCenyCesty("CPM_mini" + ".tim");
        najskorsiMoznyZaciatok();
        dobaTrvaniaProjektu();
        najneskorsiNutnyKoniec();
        krytickaCesta();
    }

    public void nacitanieCenyCesty(String cesta) {
        try {
            Scanner s = new Scanner(new File(cesta));
            dobaTrvania = new int[graf.n]; // Počet hrán
            int i = 0;
            while (s.hasNext()) {
                dobaTrvania[i] = s.nextInt();
                i++;
            }
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }


    public void najskorsiMoznyZaciatok() {
        int[] order = monotonneOcislovanie.getOcislovanie(); // Krok 1
        int[] z = new int[graf.n + 1]; // z(v)

        // Krok 2
        for (int v = 0; v < graf.n; v++) {
            z[v] = 0;
        }

        // Krok 3
        for (int k = 0; k < graf.n; k++) {
            int vk = order[k];
            for (int j = 0; j < graf.m; j++) {
                if (graf.H[j][0] == vk) {
                    int w = graf.H[j][1];
                    if (z[w] < z[vk] + dobaTrvania[k]) {
                        z[w] = z[vk] + dobaTrvania[k];
                    }
                }
            }
        }
        this.najskorsiMoznyZaciatok = z;
    }


    public void dobaTrvaniaProjektu() {
        for (int w = 0; w < graf.n+1; w++) {
            int outDegree = 0;
            for (int j = 0; j < graf.m+1; j++) {
                if (graf.H[j][0] == w) outDegree++;
            }
            if (outDegree == 0) {
                T = Math.max(T, najskorsiMoznyZaciatok[w] + dobaTrvania[w-1]);
            }
        }
    }


    private void najneskorsiNutnyKoniec() {
        int[] order = monotonneOcislovanie.getOcislovanie();
        int[] k = new int[graf.n + 1];

        for (int i = 1; i <= graf.n; i++) {
            k[i] = T;
        }

        for (int i = graf.n - 1; i >= 0; i--) {
            int vi = order[i];
            for (int j = 0; j < graf.m; j++) {
                if (graf.H[j][0] == vi) {
                    int w = graf.H[j][1];
                    int duration = dobaTrvania[i];
                    if (k[vi] > k[w] - duration) {
                        k[vi] = k[w] - duration;
                    }
                }
            }
        }

        for (int i = 0; i <= graf.n; i++) {
            najneskorsiNutnyKoniec[i] = k[i];
        }
    }
    


    public void krytickaCesta() {
        for (int j = 0; j < graf.n; j++) {
            if (najskorsiMoznyZaciatok[j] + dobaTrvania[j] == najneskorsiNutnyKoniec[j]) {
                krytickaCesta.add(j);
            }
        }
    }


    public void printInfo() {
        monotonneOcislovanie.printInfo();

        System.out.println("\nNajskorsi mozny zaciatok:");
        for (int i = 1; i < graf.n + 1; i++) {
            System.out.println("Vrchol " + (i) + ": " + najskorsiMoznyZaciatok[i]);
        }

        System.out.println("\nDoba trvania projektu: " + T);

        System.out.println("\nNajneskorsi nutny koniec:");
        for (int i = 0; i < graf.n; i++) {
            System.out.println("Vrchol " + (graf.n - i) + ": " + najneskorsiNutnyKoniec[i+1]);
        }

        System.out.print("\nKriticka cesta:");
        for (int i = 0; i < krytickaCesta.size()-1; i++) {
            System.out.print(" {" + krytickaCesta.get(i) + "},");
        }

        System.out.print("\n");
    }
}