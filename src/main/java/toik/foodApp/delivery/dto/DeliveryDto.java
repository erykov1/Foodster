package toik.foodApp.delivery.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import toik.foodApp.food.domain.Food;
import toik.foodApp.food.dto.FoodDto;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Builder
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeliveryDto {
  long deliveryId;

  Double totalCost;

  List<Food> products;

  long userId;

}
