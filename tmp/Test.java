
public class Test {

    public static void main(String[] args) {
        int n = 10;
        System.out.println(f(n));

    }

    static int f(int n) {
        System.out.println("f(" + n + ")");
        if (n == 1 || n == 2)
            return 1;

        return f(n - 1) + f(n - 2);
    }

}
