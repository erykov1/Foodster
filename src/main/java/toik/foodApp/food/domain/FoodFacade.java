package toik.foodApp.food.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.experimental.FieldDefaults;

import toik.foodApp.food.dto.FoodAlreadyAddedException;
import toik.foodApp.food.dto.FoodDto;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
public class FoodFacade {

  FoodRepository foodRepository;

  public FoodDto addNewFood(FoodDto food) {

    Optional<Food> foodDto = foodRepository.findByFoodName(food.getFoodName());

    if(foodDto.isPresent()){
      throw new FoodAlreadyAddedException("Food is already added");
    }

    Food foodAdd = Food
        .builder()
        .foodId(food.getFoodId())
        .price(food.getPrice())
        .foodName(food.getFoodName())
        .description(food.getDescription())
        .build();

    return foodRepository.save(foodAdd).dto();
  }

  public List<FoodDto> getFoodById(Collection<Long> foodIds) {
    return foodRepository.findAllByFoodIdIn(foodIds)
        .stream()
        .map(Food::dto)
        .collect(Collectors.toList());
  }

  public List<FoodDto> findAllFood() {
    return foodRepository.findAll()
        .stream()
        .map(Food::dto)
        .collect(Collectors.toList());
  }

  public Optional<FoodDto> findByFoodName(String foodName) {
    return foodRepository.findByFoodName(foodName)
        .stream()
        .filter(food -> food.getFoodName().equals(foodName))
        .findFirst()
        .map(Food::dto);
  }

}
