package toik.foodApp.user.dto;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String msg) {
    super(msg);
  }
}
