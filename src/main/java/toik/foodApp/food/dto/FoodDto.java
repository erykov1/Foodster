package toik.foodApp.food.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import toik.foodApp.food.domain.Food;

import java.util.List;


@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodDto {

  Long foodId;

  Double price;

  String foodName;

  String description;

}
