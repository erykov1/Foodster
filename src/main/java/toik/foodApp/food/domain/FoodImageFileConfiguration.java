package toik.foodApp.food.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
class FoodImageFileConfiguration {

    @Bean
    public FoodFileImageFacade foodFileImageFacade(FoodFileImageRepository foodFileImageRepository) {
        return FoodFileImageFacade.builder()
                .foodFileImageRepository(foodFileImageRepository)
                .build();
    }
}
