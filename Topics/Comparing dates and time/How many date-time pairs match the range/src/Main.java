import java.time.LocalDateTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime1 = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime dateTime2 = LocalDateTime.parse(scanner.nextLine());
        LocalDateTime start = dateTime1.isBefore(dateTime2) ? dateTime1 : dateTime2;
        LocalDateTime end = dateTime1.isAfter(dateTime2) ? dateTime1 : dateTime2;

        int n = Integer.parseInt(scanner.nextLine());
        int count = 0;
        for (int i = 0; i < n; i++) {
            LocalDateTime date = LocalDateTime.parse(scanner.nextLine());
            if (date.compareTo(start) >= 0 && date.isBefore(end)) {
                count++;
            }
        }
        System.out.println(count);
    }
}