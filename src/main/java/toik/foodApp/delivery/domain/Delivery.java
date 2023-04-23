package toik.foodApp.delivery.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import toik.foodApp.food.domain.Food;
import toik.foodApp.food.dto.FoodDto;
import toik.foodApp.delivery.dto.DeliveryDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "delivery")
@NoArgsConstructor
@Setter
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Delivery {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "delivery_id")
  @Id
  Long deliveryId;

  @Column(name = "total_cost")
  Double totalCost;

  @Column(name = "client_id")
  long userId;

  @Column(name = "delivery_type")
  String deliveryType;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  @JoinColumn(name = "delivery_id", referencedColumnName = "delivery_id", updatable = false, insertable = false)
  private List<Food> products;

  public DeliveryDto dto() {

    return DeliveryDto.builder()
        .deliveryId(deliveryId)
        .totalCost(totalCost)
        .products(products)
        .userId(userId)
        .build();
  }
}
