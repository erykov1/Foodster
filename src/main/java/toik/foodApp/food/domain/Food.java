package toik.foodApp.food.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import toik.foodApp.delivery.domain.Delivery;
import toik.foodApp.food.dto.FoodDto;

import javax.persistence.*;
import java.util.List;


@Entity(name = "food")
@Builder(toBuilder = true)
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Food {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
      @Column(name = "food_id")
  Long foodId;

  @Column(name = "price")
  Double price;

  @Column(name = "food_name")
  String foodName;

  @Column(name = "description")
  String description;

  @Column(name = "delivery_id")
  Long deliveryId;


  FoodDto dto() {
    return FoodDto.builder()
        .foodId(foodId)
        .price(price)
        .foodName(foodName)
        .description(description)
        .build();
  }
}
