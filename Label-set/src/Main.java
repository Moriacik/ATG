import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("..\\Pr1" + ".hrn");
        LabelSet l = new LabelSet(g);
        g.printInfo();
        l.printInfo();
    }
}
