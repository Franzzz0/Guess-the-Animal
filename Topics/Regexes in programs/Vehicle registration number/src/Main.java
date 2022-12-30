import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.regex.Pattern;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String regNum = scanner.nextLine(); // a valid or invalid registration number

        Pattern pattern = Pattern.compile("[ABEKMHOPCTYX]\\d{3}[ABEKMHOPCTYX]{2}");

        System.out.println(pattern.matcher(regNum).matches());
        /* write your code here */
    }
}