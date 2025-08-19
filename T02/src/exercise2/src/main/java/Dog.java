
public class Dog extends Animal {

  public Dog(String name, int age, double weight) {
    super(name, age, weight);
  }

  @Override
  public double getFeedInfoKg() {
    return this.weight * 0.3;
  }

  @Override
  public String toString() {
    return "Dog name = " + getName() + ", age = " + getAge() + ", mass = " +
        String.format("%.2f", getWeight()) + ", feed = " +
        String.format("%.2f", getFeedInfoKg());
  }
}
