import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("..\\Pr3" + ".hrn");
        //Graf g = Graf.nacitajSubor("test" + ".hrn");
        KruskalAlg k = new KruskalAlg(g);
        g.printInfo();
        k.printInfo();
    }
}