package toik.foodApp.food.dto;

public class FoodAlreadyAddedException extends RuntimeException {

  public FoodAlreadyAddedException(String msg) {
    super(msg);
  }
}
