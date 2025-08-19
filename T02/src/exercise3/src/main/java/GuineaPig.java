public class GuineaPig extends Animal implements Herbivore {

  public GuineaPig(String name, int age) {
    super(name, age);
  }

  @Override
  public String chill() {
    return "I can chill for 12 hours";
  }

  @Override
  public String toString() {
    return "GuineaPig name = " + this.name + ", age = " + this.age + ", " + chill();
  }
}