import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("..\\test3" + ".hrn");
        DepthFirstSearch d = new DepthFirstSearch(g);
        BreadthFirstSearch b = new BreadthFirstSearch(g);
        Scanner sc = new Scanner(System.in);

        g.printInfo();

        System.out.println("\nZačiatočný vrchol: ");
        int source = sc.nextInt();
        d.maximalSpanningTree(source);
        b.minimalSpanningTree(source);

        d.printInfo();
        b.printInfo();
    }
}
