package toik.foodApp.food.dto;

public class FoodNotFoundException extends RuntimeException{

  public FoodNotFoundException(String msg) {
    super(msg);
  }
}
