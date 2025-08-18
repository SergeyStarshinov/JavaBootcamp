import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int size = inputInt(sc);
    if (size <= 0) {
      System.out.println("Input error. Size <= 0");
      return;
    }
    double [] arr = new double[size];
    for (int i = 0; i < size; ++i) {
      arr[i] = inputDouble(sc);
    }
    sc.close();

    selectionSort(arr);

    printArray(arr);

  }

  static void selectionSort(double[] array) {
    for (int i = 0; i < array.length; ++i) {
      int ptr = i;
      double min = array[i];
      for (int j = i + 1; j < array.length; ++j) {
        if (array[j] < min) {
          min = array[j];
          ptr = j;
        }
      }
      array[ptr] = array[i];
      array[i] = min;
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

  static void printArray(double[] array) {
    for (double number : array) {
      System.out.print(number + " ");
    }
    System.out.println();
  }
}
