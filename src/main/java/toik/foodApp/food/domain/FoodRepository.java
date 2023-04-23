package toik.foodApp.food.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import toik.foodApp.food.dto.FoodNotFoundException;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Transactional
interface FoodRepository extends JpaRepository<Food, Long> {

  Optional<Food> findByFoodName(String foodName);

  Collection<Food> findAllByFoodIdIn(Collection<Long> foodIds);

  default Food findOneOrThrow(Long foodId) {
    Optional<Food> food = findById(foodId);
    if(food.isPresent()) {
      throw new FoodNotFoundException("Food with id : " + foodId + " not found");
    }
    return food.get();
  }

}
