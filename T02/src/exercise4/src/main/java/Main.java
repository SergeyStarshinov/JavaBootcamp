
import java.util.ArrayList;
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

    pets.stream().filter(pet -> pet.getAge() > 10).forEach(Animal::increaseAge);

    pets.forEach(System.out::println);
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

  static Animal inputPet(Scanner sc) throws IncorrectInputException {
    Animal result;
    String type;
    String name;
    int age;
    type = sc.nextLine();
    if (!"dog".equals(type) && !"cat".equals(type)) {
      throw new IncorrectInputException("Unsupported pet type");
    }
    name = sc.nextLine();
    age = inputInt(sc);
    sc.nextLine();
    if (age < 0) {
      throw new IncorrectInputException("Age <= 0");
    }
    if ("dog".equals(type)) {
      result = new Dog(name, age);
    } else {
      result = new Cat(name, age);
    }
    return result;
  }
}

