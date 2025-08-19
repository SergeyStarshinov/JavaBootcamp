
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    List<Animal> pets = new ArrayList<>();
    int count = inputInt(sc);
    sc.nextLine();
    for (int i = 0; i < count; ++i) {
      try {
        pets.add(inputPet(sc));
      } catch (IncorrectInputException e) {
        System.out.println("Incorrect input. " + e.getMessage());
      }
    }
    sc.close();

    pets.stream().filter(pet -> pet instanceof Herbivore).forEach(System.out::println);
    pets.stream().filter(pet -> pet instanceof Omnivore).forEach(System.out::println);
  }

  static Animal inputPet(Scanner sc) throws IncorrectInputException {
    HashSet<String> petTypes = new HashSet<>();
    petTypes.add("dog");
    petTypes.add("cat");
    petTypes.add("hamster");
    petTypes.add("guinea");

    Animal result;
    String type = sc.nextLine();
    if (!petTypes.contains(type)) {
      throw new IncorrectInputException("Unsupported pet type");
    }
    String name = sc.nextLine();
    int age = inputInt(sc);
    sc.nextLine();
    if (age < 0) {
      throw new IncorrectInputException("Age <= 0");
    }

    switch (type) {
      case "dog" ->
          result = new Dog(name, age);
      case "cat" ->
          result = new Cat(name, age);
      case "hamster" ->
          result = new Hamster(name, age);
      default ->
          result = new GuineaPig(name, age);
    }
    return result;
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

