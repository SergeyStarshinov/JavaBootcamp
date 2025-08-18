import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    if (!sc.hasNextInt()) {
      System.out.println("Input error");
      return;
    }

    int oldInt = sc.nextInt();
    int newInt;
    int index = 0;
    boolean isCorrectSequence = true;
    while (isCorrectSequence && sc.hasNextInt()) {
      newInt = sc.nextInt();
      ++index;
      if (newInt < oldInt) isCorrectSequence = false;
      oldInt = newInt;
    }
    sc.close();

    if (isCorrectSequence) {
      System.out.println("The sequence is ordered in ascending order");
    } else {
      System.out.println("The sequence is not ordered from the ordinal number of the number " + index);
    }
  }
}


