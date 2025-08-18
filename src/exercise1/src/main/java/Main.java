import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double x1 = 0, x2 = 0, x3 = 0;
        double y1 = 0, y2 = 0, y3 = 0;
        Scanner sc = new Scanner(System.in);
        x1 = inputDouble(sc);
        y1 = inputDouble(sc);
        x2 = inputDouble(sc);
        y2 = inputDouble(sc);
        x3 = inputDouble(sc);
        y3 = inputDouble(sc);
        sc.close();
        double edge1 = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double edge2 = Math.sqrt((x3 - x2) * (x3 - x2) + (y3 - y2) * (y3 - y2));
        double edge3 = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));

        if (edge1 < 1e-8 || edge2 < 1e-8 || edge3 < 1e-8 || Math.abs(edge1 - edge2 - edge3) < 1e-8 ||
                Math.abs(edge2 - edge1 - edge3) < 1e-8 || Math.abs(edge3 - edge1 - edge2) < 1e-8) {
            System.out.println("It isn't triangle");
        } else {
            System.out.printf("Perimeter: %.3f\n", edge1 + edge2 + edge3);
        }
    }

    static double inputDouble(Scanner sc) {
        double result = 0;
        boolean flag = true;
        while (flag) {
            if (sc.hasNextDouble()) {
                result = sc.nextDouble();
                flag = false;
            } else {
                System.out.println("Couldn't parse a number. Please, try again");
                sc.next();
            }
        }
        return result;
    }
}