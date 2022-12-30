import java.time.LocalTime;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String[] parts1 = scanner.nextLine().split(" ");
        String[] parts2 = scanner.nextLine().split(" ");

        System.out.println(checkIntersect(parts1, parts2));
    }

    private static boolean checkIntersect(String[] parts1, String[] parts2) {
        LocalTime beginning1 = LocalTime.parse(parts1[0]);
        LocalTime end1 = LocalTime.parse(parts1[1]);
        LocalTime beginning2 = LocalTime.parse(parts2[0]);
        LocalTime end2 = LocalTime.parse(parts2[1]);

        if (beginning1.isAfter(end2) || beginning2.isAfter(end1)) {
            return false;
        } else {
            return true;
        }
    }
}