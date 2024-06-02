import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("..\\test" + ".hrn");
        MonotonOcisl m = new MonotonOcisl(g);
        g.printInfo();
        m.printInfo();
    }
}
