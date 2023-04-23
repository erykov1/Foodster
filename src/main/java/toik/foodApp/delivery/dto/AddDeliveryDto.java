package toik.foodApp.delivery.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AddDeliveryDto {

  private final Double cost;

  private final Long userId;

}
