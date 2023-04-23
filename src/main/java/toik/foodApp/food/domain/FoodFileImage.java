package toik.foodApp.food.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import toik.foodApp.food.dto.FoodFileImageDto;

import javax.persistence.*;


@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Entity(name = "food_image")
@NoArgsConstructor
@Builder
@Getter
class FoodFileImage {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    String name;

    String type;

    @Lob
    byte[] data;

    FoodFileImageDto dto() {
        return FoodFileImageDto.builder()
                .name(name)
                .type(type)
                .data(data)
                .build();
    }
}
