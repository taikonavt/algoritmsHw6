import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static final int MIN = -100;
    public static final int MAX = 100;

    public static void main(String[] args) {
        Tree tree = new Tree();

        for (int i = 0; i < 20; i++) {
            int a = ThreadLocalRandom.current().nextInt(MIN, MAX + 1);
            tree.insert(a);
            System.out.print(a + " ");
        }
        System.out.println();

        tree.analyze();
    }
}
