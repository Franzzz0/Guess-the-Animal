import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // start coding here
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int h = scanner.nextInt();

        System.out.println((h < a) ? "Deficiency" : (h > b) ? "Excess" : "Normal");
    }
}