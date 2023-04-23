package toik.foodApp.domain.food

import spock.lang.Specification
import toik.foodApp.food.domain.FoodConfiguration
import toik.foodApp.food.domain.FoodFacade
import toik.foodApp.food.dto.FoodAlreadyAddedException
import toik.foodApp.food.dto.FoodDto

class FoodSpec extends Specification {

  private static FAKE_FOOD_ID = 34L

  private FoodFacade foodFacade = new FoodConfiguration().foodFacade()

  private final static FoodDto SOUP_SAMPLE = new FoodDto(1L, 12.5, "soup", "simple soup")
  private final static FoodDto MARGHERITA_SAMPLE = new FoodDto(2L, 30.0,
      "margherita", "pizza")


  def "should add new food to database" () {
    when: "new food is added to database"
      foodFacade.addNewFood(SOUP_SAMPLE)
    then: "database contains this food"
      Optional<FoodDto> foodDtoResult = foodFacade.findByFoodName(SOUP_SAMPLE.getFoodName())
      foodDtoResult.get().getFoodId() == SOUP_SAMPLE.getFoodId()
      foodDtoResult.get().getFoodName() == SOUP_SAMPLE.getFoodName()
      foodDtoResult.get().getDescription() == SOUP_SAMPLE.getDescription()
      foodDtoResult.get().getPrice() == SOUP_SAMPLE.getPrice()
  }

  def "should not find food if the name of food is not in system" () {
    when: "system is asked for food that is not in system"
      Optional<FoodDto> foodDtoResult = foodFacade.findByFoodName("food not in system")
    then: "returns empty object"
      foodDtoResult.isEmpty()
  }

  def "should not find food if user not pass name of food" () {
    when: "system is asked for food without name"
      Optional<FoodDto> foodDtoResult = foodFacade.findByFoodName(null)
    then: "returns empty object"
      foodDtoResult.isEmpty()
  }

  def "should find all food in database" () {
    given: "there is some food in system"
      foodFacade.addNewFood(SOUP_SAMPLE)
      foodFacade.addNewFood(MARGHERITA_SAMPLE)
    when: "system is asked for all food in system"
      List<FoodDto> foodDtoResult = foodFacade.findAllFood()
    then: "system returns all food in the database"
      foodDtoResult.size() == 2
      foodDtoResult.get(0).getFoodId() == SOUP_SAMPLE.getFoodId()
      foodDtoResult.get(0).getPrice() == SOUP_SAMPLE.getPrice()
      foodDtoResult.get(0).getDescription() == SOUP_SAMPLE.getDescription()
      foodDtoResult.get(0).getFoodName() == SOUP_SAMPLE.getFoodName()
      foodDtoResult.get(1).getFoodId() == MARGHERITA_SAMPLE.getFoodId()
      foodDtoResult.get(1).getPrice() == MARGHERITA_SAMPLE.getPrice()
      foodDtoResult.get(1).getDescription() == MARGHERITA_SAMPLE.getDescription()
      foodDtoResult.get(1).getFoodName() == MARGHERITA_SAMPLE.getFoodName()
  }

  def "should return only food that is in database" () {
    given: "there is some food in system"
      foodFacade.addNewFood(SOUP_SAMPLE)
    when: "system is asked for food - one that is in database and one not"
      List<FoodDto> foodDtoResult = foodFacade.getFoodById([SOUP_SAMPLE.getFoodId(), FAKE_FOOD_ID])
    then: "system returns food that is in database"
      foodDtoResult.size() == 1
      foodDtoResult.get(0).getFoodId() == SOUP_SAMPLE.getFoodId()
      foodDtoResult.get(0).getPrice() == SOUP_SAMPLE.getPrice()
      foodDtoResult.get(0).getDescription() == SOUP_SAMPLE.getDescription()
      foodDtoResult.get(0).getFoodName() == SOUP_SAMPLE.getFoodName()

  }

  def "should return only valid food" () {
    given: "there is some food in system"
      foodFacade.addNewFood(MARGHERITA_SAMPLE)
    when: "system is asked for food - one that is valid and one not"
      List<FoodDto> foodDtoResult = foodFacade.getFoodById([MARGHERITA_SAMPLE.getFoodId(), null])
    then: "system returns food that is in database"
      foodDtoResult.size() == 1
      foodDtoResult.get(0).getFoodId() == MARGHERITA_SAMPLE.getFoodId()
      foodDtoResult.get(0).getPrice() == MARGHERITA_SAMPLE.getPrice()
      foodDtoResult.get(0).getDescription() == MARGHERITA_SAMPLE.getDescription()
      foodDtoResult.get(0).getFoodName() == MARGHERITA_SAMPLE.getFoodName()
  }

  def "should throw exception when food is already added" () {
    given: "there is some food in database"
      foodFacade.addNewFood(MARGHERITA_SAMPLE)
    when: "employee try to add same food"
      foodFacade.addNewFood(MARGHERITA_SAMPLE)
    then: "system throw exception"
      thrown(FoodAlreadyAddedException)
  }

}
