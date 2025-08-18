import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int n = inputInt();
    if (n < 0) {
      System.out.println("Input error. Size <= 0");
    } else if (n > 46) {
      System.out.println("Too large n");
    } else try {
      System.out.println(fib(n));
    } catch (StackOverflowError e) {
      System.out.println("Too large n");
    }
  }

  static int fib(int n) {
    return switch (n) {
      case 0 -> 0;
      case 1 -> 1;
      default -> fib(n - 1) + fib(n - 2);
    };
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
}