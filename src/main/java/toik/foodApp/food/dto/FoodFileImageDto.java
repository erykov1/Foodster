package toik.foodApp.food.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

/**
 * Class that represents food image data transfer object
 */
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FoodFileImageDto {

    String name;

    String type;

    byte[] data;

}
