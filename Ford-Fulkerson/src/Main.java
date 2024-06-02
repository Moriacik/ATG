import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("..\\Tok_mini" + ".hrn");
        //Graf g = Graf.nacitajSubor("..\\test2" + ".hrn");
        FordFulkerson f = new FordFulkerson(g);
        Scanner sc = new Scanner(System.in);

        g.printInfo();

        System.out.println("\nZačiatočný vrchol: ");
        int source = sc.nextInt();

        System.out.println("Cieľový vrchol:");
        int destination = sc.nextInt();

        f.fordFulkerson(source, destination);
        f.printInfo();
    }
}