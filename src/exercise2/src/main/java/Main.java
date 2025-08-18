import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int seconds = inputInt();
        int ss = getTime(seconds, 's');
        int mm = getTime(seconds, 'm');
        int hh = getTime(seconds, 'h');

        printTime(seconds, hh, mm, ss);
    }

    static int inputInt() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean flag = true;
        while (flag) {
            if (sc.hasNextInt()) {
                result = sc.nextInt();
                flag = false;
            } else {
                System.out.println("Couldn't parse a number. Please, try again");
                sc.next();
            }
        }
        sc.close();
        return result;
    }

    static int getTime(int seconds, char interval) {
        int ss = seconds % 60;
        if (interval == 's') return ss;
        seconds /= 60;
        int mm = seconds % 60;
        if (interval == 'm') return mm;
        seconds /= 60;
        int hh = seconds % 24;
        return hh;
    }

    static void printTime(int seconds, int hh, int mm, int ss) {
        if (seconds < 0) {
            System.out.println("Incorrect time");
        } else {
            System.out.printf("%02d:%02d:%02d\n", hh, mm, ss);
        }
    }
}