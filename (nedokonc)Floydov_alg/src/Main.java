import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("Pr1" + ".hrn");
        FloydAlg f = new FloydAlg(g);
        g.printInfo();
        f.printInfo();
    }
}
