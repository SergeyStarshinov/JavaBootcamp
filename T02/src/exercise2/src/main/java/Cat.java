
public class Cat extends Animal {

  public Cat(String name, int age, double weight) {
    super(name, age, weight);
  }

  @Override
  public double getFeedInfoKg() {
    return this.weight * 0.1;
  }

  @Override
  public String toString() {
    return "Cat name = " + getName() + ", age = " + getAge() + ", mass = " +
        String.format("%.2f", getWeight()) + ", feed = " +
        String.format("%.2f", getFeedInfoKg());
  }
}
