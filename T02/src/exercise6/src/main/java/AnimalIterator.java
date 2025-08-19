
import java.util.List;

public class AnimalIterator implements BaseIterator {

  private final List<Animal> pets;
  private int index;

  public AnimalIterator(List<Animal> pets, int index) {
    this.pets = pets;
    this.index = index;
  }

  @Override
  public Animal next() {
    Animal pet = pets.get(index);
    ++index;
    return pet;
  }

  @Override
  public boolean hasNext() {
    return index < pets.size();
  }

  @Override
  public void reset() {
    index = 0;
  }
}

