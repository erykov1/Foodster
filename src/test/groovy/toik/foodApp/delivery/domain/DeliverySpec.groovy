package toik.foodApp.delivery.domain

import spock.lang.Specification
import toik.foodApp.delivery.dto.DeliveryDto
import toik.foodApp.food.domain.Food

class DeliverySpec extends Specification {
  static Long FAKE_ID = 45l

  static Food margherita = new Food(1L, 10.0, "Pizza Margherita",
      "Classic Italian pizza with tomato sauce and mozzarella cheese", 1L)
  static Food carbonara = new Food(2L, 12.5, "Spaghetti Carbonara",
      "Pasta with bacon, eggs, and Parmesan cheese", 1L)
  static Food salad = new Food(3L, 8.0, "Caesar Salad",
      "Salad made with romaine lettuce, croutons, Parmesan cheese, and Caesar dressing", 1L)
  static Food sushi = new Food(4L, 15.0, "Sushi Platter",
      "Assortment of sushi rolls and sashimi", 2L)
  static Food soup = new Food(5L, 7.5, "Miso Soup",
      "Traditional Japanese soup with tofu and seaweed", 2L)

  static final List<Food> SAMPLE_PRODUCTS_FOR_FIRST_USER = Arrays.asList(margherita, carbonara, salad)

  static final List<Food> SECOND_SAMPLE_PRODUCTS_FOR_FIRST_USER = Arrays.asList(carbonara, salad)

  static final List<Food> SAMPLE_PRODUCTS_FOR_SECOND_USER = Arrays.asList(sushi, soup)

  public final static DeliveryDto DELIVERY_FOR_FIRST_USER = createDeliveryDto(1L, 30.5,
      SAMPLE_PRODUCTS_FOR_FIRST_USER, 11L)

  public final static DeliveryDto DELIVERY_FOR_SECOND_USER = createDeliveryDto(2L, 22.5,
      SAMPLE_PRODUCTS_FOR_SECOND_USER, 12L)

  public final static DeliveryDto SECOND_DELIVERY_FOR_FIRST_USER = createDeliveryDto(3L, 22.5,
      SECOND_SAMPLE_PRODUCTS_FOR_FIRST_USER, 11L)

  private static DeliveryDto createDeliveryDto(Long deliveryId, Double totalCost, List<Food> products,
                                               Long userId) {

    return DeliveryDto.builder()
        .deliveryId(deliveryId)
        .totalCost(totalCost)
        .products(products)
        .userId(userId)
        .build()
  }

  private DeliveryFacade deliveryFacade = new DeliveryConfiguration().deliveryFacade()


  def "should create new delivery and add it to the database" () {
    when: "delivery is created"
      DeliveryDto deliveryDto = deliveryFacade.addNewDelivery(new DeliveryDto(1L, 30.5,
          SAMPLE_PRODUCTS_FOR_FIRST_USER, 11l))
    then: "database contains this delivery"
      Optional<DeliveryDto> deliveryDtoResult = deliveryFacade.findByDeliveryId(deliveryDto.deliveryId)
      deliveryDtoResult.get().deliveryId == deliveryDto.deliveryId
      deliveryDtoResult.get().userId == deliveryDto.userId
      deliveryDtoResult.get().products == deliveryDto.products
      deliveryDtoResult.get().totalCost == deliveryDto.totalCost
  }

  def "should return empty when system is asked for delivery that does not exist" () {
    when: "system is asked for delivery that does not exist"
      Optional<DeliveryDto> deliveryDtoResult = deliveryFacade.findByDeliveryId(null)
    then: "system returns empty"
      deliveryDtoResult.isEmpty()
  }

  def "should return one delivery when is asked for one correct delivery and one not" () {
    given: "there are two deliveries in system"
      deliveryFacade.addNewDelivery(DELIVERY_FOR_FIRST_USER)
      deliveryFacade.addNewDelivery(DELIVERY_FOR_SECOND_USER)
    when: "system is asked for one delivery that is in database and one not"
      List<DeliveryDto> deliveryDtoResult = deliveryFacade.getAllDeliveryById([DELIVERY_FOR_FIRST_USER.deliveryId,
        FAKE_ID])
    then: "system returns one delivery"
      deliveryDtoResult.size() == 1
      deliveryDtoResult.deliveryId.get(0) == DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(0) == DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(0) == DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(0) == DELIVERY_FOR_FIRST_USER.products
  }

  def "should return one delivery when is asked for one correct delivery and one that does not exist" () {
    given: "there are two deliveries in system"
      deliveryFacade.addNewDelivery(DELIVERY_FOR_FIRST_USER)
      deliveryFacade.addNewDelivery(DELIVERY_FOR_SECOND_USER)
    when: "system is asked for one delivery that is in database and one that not exist"
    List<DeliveryDto> deliveryDtoResult = deliveryFacade.getAllDeliveryById([DELIVERY_FOR_FIRST_USER.deliveryId,
                                                                             null])
    then: "system returns one delivery"
      deliveryDtoResult.size() == 1
      deliveryDtoResult.deliveryId.get(0) == DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(0) == DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(0) == DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(0) == DELIVERY_FOR_FIRST_USER.products
  }

  def "should return all user's orders" () {
    given: "there are two orders from one user"
      deliveryFacade.addNewDelivery(DELIVERY_FOR_FIRST_USER)
      deliveryFacade.addNewDelivery(SECOND_DELIVERY_FOR_FIRST_USER)
    when: "system is asked for deliveries that contain the same user id"
      List<DeliveryDto> deliveryDtoResult = deliveryFacade.findDeliveryByUserId(11l)
    then: "system returns all user's deliveries"
      deliveryDtoResult.deliveryId.get(0) == DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(0) == DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(0) == DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(0) == DELIVERY_FOR_FIRST_USER.products
      deliveryDtoResult.deliveryId.get(1) == SECOND_DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(1) == SECOND_DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(1) == SECOND_DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(1) == SECOND_DELIVERY_FOR_FIRST_USER.products
  }

  def "should return all deliveries" () {
    given: "there are three orders in system"
      deliveryFacade.addNewDelivery(DELIVERY_FOR_FIRST_USER)
      deliveryFacade.addNewDelivery(SECOND_DELIVERY_FOR_FIRST_USER)
      deliveryFacade.addNewDelivery(DELIVERY_FOR_SECOND_USER)
    when: "system is asked for all deliveries"
      List<DeliveryDto> deliveryDtoResult = deliveryFacade.getAllDelivery()
    then: "system returns all deliveries"
      deliveryDtoResult.deliveryId.get(0) == DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(0) == DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(0) == DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(0) == DELIVERY_FOR_FIRST_USER.products
      deliveryDtoResult.deliveryId.get(1) == DELIVERY_FOR_SECOND_USER.deliveryId
      deliveryDtoResult.userId.get(1) == DELIVERY_FOR_SECOND_USER.userId
      deliveryDtoResult.totalCost.get(1) == DELIVERY_FOR_SECOND_USER.totalCost
      deliveryDtoResult.products.get(1) == DELIVERY_FOR_SECOND_USER.products
      deliveryDtoResult.deliveryId.get(2) == SECOND_DELIVERY_FOR_FIRST_USER.deliveryId
      deliveryDtoResult.userId.get(2) == SECOND_DELIVERY_FOR_FIRST_USER.userId
      deliveryDtoResult.totalCost.get(2) == SECOND_DELIVERY_FOR_FIRST_USER.totalCost
      deliveryDtoResult.products.get(2) == SECOND_DELIVERY_FOR_FIRST_USER.products
  }


}
