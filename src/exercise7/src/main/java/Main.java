import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    String fileName = inputFileName();

    int size;
    double[] array;
    try (Scanner sc = new Scanner(new File(fileName))) {
      size = inputInt(sc);
      if (size <= 0) {
        System.out.println("Input error. Size <= 0");
      } else {
        array = new double[size];
        if (inputArray(array, size, sc)) {
          System.out.println(size);
          printArray(array);
          findMinMax(array);
        } else {
          System.out.println("Input error. Insufficient number of elements");
        }
      }
    } catch (FileNotFoundException e) {
      System.out.println("Input error. File isn't exist");
    }
  }

  static String inputFileName() {
    Scanner sc = new Scanner(System.in);
    String fileName = sc.nextLine().trim();
    sc.close();
    return fileName;
  }

  static int inputInt(Scanner sc) {
    int result = 0;
    boolean flag = true;
    while (flag && sc.hasNextLine()) {
      if (sc.hasNextInt()) {
        result = sc.nextInt();
        flag = false;
      } else {
        sc.next();
      }
    }
    return result;
  }

  static boolean inputArray(double[] array, int size, Scanner sc){
    int count = 0;
    while (count < size && sc.hasNextLine()) {
      if (sc.hasNextDouble()) {
        array[count++] = sc.nextDouble();
      } else {
        sc.next();
      }
    }
    return count == size;
  }

  static void printArray(double[] array) {
    for (double number : array) {
      System.out.print(number + " ");
    }
    System.out.println();
  }

  static void findMinMax(double[] array) {
    double min = array[0];
    double max = array[0];
    for (double number : array) {
      if (number < min) min = number;
      if (number > max) max = number;
    }
    try {
      FileWriter writer = new FileWriter("result.txt");
      writer.write(min + " " + max);
      writer.close();
      System.out.println("Saving min and max values in file");
    } catch (IOException e) {
      System.out.println("File write error");
    }
  }
}

