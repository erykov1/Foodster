package toik.foodApp.food.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public
class FoodConfiguration {

  public FoodFacade foodFacade() {

    return FoodFacade.builder()
        .foodRepository(new InMemoryFoodRepository())
        .build();
  }

  @Bean
  public FoodFacade foodFacade(FoodRepository foodRepository) {
    return FoodFacade.builder()
        .foodRepository(foodRepository)
        .build();
  }
}
