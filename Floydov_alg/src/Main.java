import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Graf g = Graf.nacitajSubor("../graf.txt");
            g.printInfo();
            System.out.println();

            System.out.println("zaciatocny vrchol: ");
            int source = sc.nextInt();

            System.out.println("koncovy vrchol: ");
            int destination = sc.nextInt();
            System.out.println();

            FloydAlg f = new FloydAlg(g);

            // Príklad: Vytlačenie cesty z vrcholu 3 do vrcholu 4
            f.vytlacCestu(source - 1, destination - 1);

        } catch (FileNotFoundException e) {
            System.out.println("Súbor nebol nájdený");
        }
    }
}