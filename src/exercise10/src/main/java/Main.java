import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    Integer number = inputInt(sc);
    sc.nextLine();
    List<User> users = new ArrayList<>();
    for (int i = 0; i < number; ++i) {
      users.add(inputUser(sc));
    }
    sc.close();

    printList(users);
  }

  static User inputUser(Scanner sc) {
    boolean flag = true;
    String name = "";
    Integer age = 0;
    while (flag) {
      name = sc.nextLine();
      age = inputInt(sc);
      sc.nextLine();
      if (age <= 0) {
        System.out.println("Incorrect input. Age <= 0");
      } else {
        flag = false;
      }
    }
    return new User(name, age);
  }

  static Integer inputInt(Scanner sc) {
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

  static void printList(List<User> users) {
    String str = users.stream().
        filter(user -> user.getUserAge() >= 18).
        map(User::getUserName).
        collect(Collectors.joining(", "));
    System.out.println(str);
  }

}
