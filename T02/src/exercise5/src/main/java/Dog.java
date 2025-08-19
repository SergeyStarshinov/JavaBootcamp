import java.util.concurrent.TimeUnit;

public class Dog extends Animal {

  public Dog(String name, int age) {
    super(name, age);
  }

  @Override
  public String toString() {
    return "Dog name = " + getName() + ", age = " + getAge();
  }

  @Override
  public double goToWalk() {
    double walkTime = getAge() * 0.5;
    try {
      TimeUnit.SECONDS.sleep((long) walkTime);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    return walkTime;
  }

}