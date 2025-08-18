import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);

    Integer number = inputInt(sc);
    sc.nextLine();

    List<String> strings = new ArrayList<>();
    for (int i = 0; i < number; ++i) {
      strings.add(sc.nextLine());
    }
    String filter = sc.nextLine();
    sc.close();

    printFilteredList(strings, filter);
  }

  static Integer inputInt(Scanner sc) {
    Integer result = 0;
    Boolean flag = true;
    while (flag && sc.hasNextLine()) {
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

  static void printFilteredList(List<String> strings, String filter) {
    boolean firstLine = true;
    for (String line : strings) {
      if (line.contains(filter)) {
        if (firstLine) {
          firstLine = false;
        } else {
          System.out.print(", ");
        }
        System.out.print(line);
      }
    }
    System.out.println();
  }
}
