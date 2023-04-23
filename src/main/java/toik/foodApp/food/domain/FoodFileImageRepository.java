package toik.foodApp.food.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Transactional
public interface FoodFileImageRepository extends JpaRepository<FoodFileImage, Long> {
    Collection<FoodFileImage> findAllByIdIn(Collection<Long> foodIds);

    Optional<FoodFileImage> findFoodFileImageById(Long foodFileImageId);

}
