import java.util.Random;

public class Test {
    public static void main(String[] args) {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int r = random.nextInt(2);
            System.out.println(r);
        }

    }
}
