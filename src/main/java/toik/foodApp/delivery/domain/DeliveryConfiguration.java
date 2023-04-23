package toik.foodApp.delivery.domain;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class DeliveryConfiguration {

  public DeliveryFacade deliveryFacade() {

    return DeliveryFacade.builder()
        .deliveryRepository(new InMemoryDeliveryRepository())
        .build();
  }

  @Bean
  public DeliveryFacade deliveryFacade(DeliveryRepository deliveryRepository) {
    return DeliveryFacade.builder()
        .deliveryRepository(deliveryRepository)
        .build();
  }
}
