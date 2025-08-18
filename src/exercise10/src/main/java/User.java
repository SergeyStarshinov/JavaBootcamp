public class User {
  private final String userName;
  public final int userAge;

  public User(String userName, int userAge) {
    this.userName = userName;
    this.userAge = userAge;
  }

  public String getUserName() {
    return userName;
  }

  public int getUserAge() {
    return userAge;
  }

}
