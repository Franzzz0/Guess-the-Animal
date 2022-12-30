import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        int n = scanner.nextInt();

        StringBuilder string = new StringBuilder();

        char uppercaseCharacter = 'A';
        char lowercaseCharacter = 'c';
        int digit = 0;
        while (string.length() < n) {
            for (int i = 0; i < a; i++) {
                char charToAppend = i % 2 == 0 ? uppercaseCharacter : (char) (uppercaseCharacter + 2);
                string.append(charToAppend);
            }

            for (int i = 0; i < b; i++) {
                char charToAppend = i % 2 == 0 ? lowercaseCharacter : (char) (lowercaseCharacter + 2);
                string.append(charToAppend);
            }
            for (int i = 1; i <= c; i++) {
                int digitToAppend = i % 2 == 0 ? digit : digit + 2;
                string.append(digitToAppend);
            }
            if (a == 0 && b == 0 && c == 0) {
                a = n;
            }
            uppercaseCharacter++;
            lowercaseCharacter++;
            digit = digit == 0 ? 1 : 0;
        }

        System.out.println(string.substring(0, n));
        // write your code here
    }
}