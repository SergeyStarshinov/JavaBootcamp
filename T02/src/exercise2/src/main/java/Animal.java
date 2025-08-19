
abstract class Animal {

  protected final String name;
  protected final int age;
  protected final double weight;

  public Animal(String name, int age, double weight) {
    this.name = name;
    this.age = age;
    this.weight = weight;
  }

  public String getName() {
    return this.name;
  }

  public int getAge() {
    return this.age;
  }

  public double getWeight() {
    return this.weight;
  }

  abstract double getFeedInfoKg();
}
