import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int size = inputInt(sc);
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }
    int [] arr = new int[size];
    for(int i = 0; i < size; ++i) {
      arr[i] = inputInt(sc);
    }
    sc.close();

    int[] result = new int[size];
    int count = 0;
    int i = 0;
    while (i < size) {
      if (haveSameFirstLastDigits(arr[i])) {
        result[count++] = arr[i];
      }
      ++i;
    }
    printArray(result, count);

  }

  static boolean haveSameFirstLastDigits(int number) {
    if (number < 0) number *= -1;
    int lastDigit = number % 10;
    while (number >= 10) {
      number /= 10;
    }
    return number == lastDigit;
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

  static void printArray(int[] array, int count) {
    if (count != 0) {
      for (int i = 0; i < count; ++i) {
        System.out.print(array[i] + " ");
      }
      System.out.println();
    } else {
      System.out.println("There are no such elements");
    }
  }
}
