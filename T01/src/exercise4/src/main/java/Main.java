import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int number = inputInt(sc);
    if (number <= 0) {
        System.out.println("Input error. Size <= 0");
        return;
    }
    int [] arr = new int[number];
    for(int i = 0; i < number; ++i) {
      arr[i] = inputInt(sc);
    }
    sc.close();

    int sum = 0;
    int count = 0;
    int i = 0;
    while (i < number) {
      if (arr[i] < 0) {
        sum += arr[i];
        ++count;
      }
      ++i;
    }
    if (count == 0) {
      System.out.println("There are no negative elements");
    } else {
      System.out.println(sum / count);
    }
  }

  static int inputInt(Scanner sc) {
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
    return result;
  }
}