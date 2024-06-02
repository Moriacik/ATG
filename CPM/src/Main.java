import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        Graf g = Graf.nacitajSubor("CPM_mini" + ".hrn");
        //Graf g = Graf.nacitajSubor("test2" + ".hrn");
        CPM c = new CPM(g);
        g.printInfo();
        c.printInfo();
    }
}