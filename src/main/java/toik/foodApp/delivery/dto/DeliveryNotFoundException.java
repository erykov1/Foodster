package toik.foodApp.delivery.dto;

public class DeliveryNotFoundException extends RuntimeException {

  public DeliveryNotFoundException(String msg) {
    super(msg);
  }
}
